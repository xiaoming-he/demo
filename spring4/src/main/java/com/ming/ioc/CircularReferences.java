package com.ming.ioc;

/**
 * @author ming_he
 * @date 2018/7/3 23:13
 */
public class CircularReferences {

    public class TestA {
        private TestB testB;

        public void setTestB(TestB testB) {
            this.testB = testB;
        }
    }

    public class TestB {
        private TestC testC;

        public void setTestC(TestC testC) {
            this.testC = testC;
        }
    }

    public class TestC {
        private TestA testA;

        public void setTestA(TestA testA) {
            this.testA = testA;
        }
    }

    public void create() {
        TestA testA = new TestA();
        TestB testB = new TestB();
        TestC testC = new TestC();
        testA.setTestB(testB);
        testB.setTestC(testC);
        testC.setTestA(testA);
    }
}


