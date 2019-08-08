package com.ming.quartz;

import com.ming.annotations.EnableQuartzScheduled;
import com.ming.annotations.QuartzScheduled;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author ming_he
 * @date 2019/7/1 12:21 AM
 */
public class QuartzScheduledRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanClassLoaderAware, EnvironmentAware, BeanFactoryAware {

    protected final Log logger = LogFactory.getLog(getClass());

    private Environment environment;

    private BeanFactory beanFactory;

    private ClassLoader classLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annoAttrs =  AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableQuartzScheduled.class.getName()));
        for (String pkg : annoAttrs.getStringArray("basePackage")) {
            if (StringUtils.hasText(pkg)) {
                registerQuartzScheduled(pkg);
            }
        }
    }

    private void registerQuartzScheduled(String basePackage) {
        //扫描所有的类
        ClassPathScanningCandidateComponentProvider classScanner = new ClassPathScanningCandidateComponentProvider(false, this.environment);
        AnnotationTypeFilter  annotationTypeFilter = new AnnotationTypeFilter(QuartzScheduled.class);
        classScanner.addIncludeFilter(annotationTypeFilter);
        Set<BeanDefinition> beanDefinitionSet = classScanner.findCandidateComponents(basePackage);
        if (beanDefinitionSet == null || beanDefinitionSet.isEmpty()) {
            return;
        }
        for (BeanDefinition beanDefinition : beanDefinitionSet) {
            if (beanDefinition instanceof AnnotatedBeanDefinition) {
                registerBeans(((AnnotatedBeanDefinition) beanDefinition));
            }
        }
     }

    private void registerBeans(AnnotatedBeanDefinition annotatedBeanDefinition) {
        String className = annotatedBeanDefinition.getBeanClassName();
        //得到job上的注解
        AnnotationMetadata metadata = annotatedBeanDefinition.getMetadata();
        AnnotationAttributes annoAttrs =  AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(QuartzScheduled.class.getName()));
        //获取注解上cron的值
        String cron = annoAttrs.getString("cron");
        if (StringUtils.isEmpty(cron) || !CronExpression.isValidExpression(cron)) {
            logger.error(className + " @QuartzScheduled cron error");
            return;
        }
        try {
            Class jobClass = ClassUtils.forName(annotatedBeanDefinition.getMetadata().getClassName(), classLoader);
            //生成jobDetail
            JobDetail jobDetail = JobBuilder.newJob(jobClass).storeDurably().withIdentity(jobClass.getSimpleName() + "_jobDetail").build();
            //生成trigger
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionIgnoreMisfires();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(jobClass.getSimpleName() + "_trigger").withSchedule(cronScheduleBuilder).build();
            //注册bean
            ((DefaultListableBeanFactory) this.beanFactory).registerSingleton(jobClass.getSimpleName() + "_jobDetail", jobDetail);
            ((DefaultListableBeanFactory) this.beanFactory).registerSingleton(jobClass.getSimpleName() + "_trigger", cronTrigger);
        } catch (Exception e) {
            logger.error("register" + className + " error", e);
        }

    }
}
