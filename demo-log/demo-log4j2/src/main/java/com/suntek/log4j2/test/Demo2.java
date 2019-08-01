package com.suntek.log4j2.test;

/**
 * @author： wuyuhao
 * @version： 2019/03/ 18
 * @since： 2019/03/18
 */
public class Demo2 {
    public static void main(String[] args) {
        String greeting = "Hello";
        if (greeting == "Hello") {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        if (greeting.substring(0, 3) == "Hel") {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        String greeting2 = "Hello";
        if (greeting.substring(0, 3).compareTo("Hel") == 0) {
            System.out.println("true");
        }

        if (greeting.equals(greeting2)) {
            System.out.println("true");
        }

        int n = greeting.length();
        System.out.println(n);

        int cpCount = greeting.codePointCount(0, greeting.length());
        System.out.println(cpCount);

        char firstChar = greeting.charAt(0);
        System.out.println(firstChar);

        char lastChar = greeting.charAt(4);
        System.out.println(lastChar);

        int i = greeting.offsetByCodePoints(0, 4);
        int cp = greeting.codePointAt(i);
        System.out.println(i);
        System.out.println(cp);

        String sentence = "uu is the set";
        char c = sentence.charAt(1);
        System.out.println(c);

        int[] codePoints = sentence.codePoints().toArray();
        for (int codePoint : codePoints) {
            System.out.print(" " + codePoint);
        }
        System.out.println();
        String s = new String(codePoints, 0, codePoints.length);
        System.out.println(s);

        String d = " i am big bang big";
        System.out.println(d.indexOf("am"));
        System.out.println(d.indexOf("m b"));
        System.out.println(d.indexOf("big"));
        System.out.println(d.indexOf(" big"));
        System.out.println(d.indexOf("big", 14));
        System.out.println(d.indexOf(" bag"));
        System.out.println(d.lastIndexOf("big", 9));
        System.out.println(d.lastIndexOf("big"));

        String aa = "I am a poor guy";
        StringBuffer sb = new StringBuffer("good");
        aa = aa.replace("poor", sb);
        System.out.println(aa);
        StringBuilder sb1 = new StringBuilder("aaaaaaaaaaaaaaaa");
        sb1.setCharAt(sb1.length()-1,'c');
        System.out.println(sb1.toString());
        //java 保留了 printf 方法
        System.out.printf("Hello,%s,Next year,you'll be %d","wuyuhao",18);
        System.out.println();
        double x = 1000.0/3.0;
        System.out.println(x);
        System.out.printf("%8.2f",x);
        System.out.println();
        String a = "wuyuhao";
        int b = 19;
        String format = String.format("Hello,%s,you will be rich in %d years", a, b);
        System.out.println(format);

    }
}
