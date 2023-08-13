package algorithem.listlearn;

import org.junit.Test;

public class MyStringTest {
    @Test
    public void testStrring(){

        //，创建对象newm,new q；检查字符串常量池是否有字符串m和n，如果没有，添加m,n字符串，返回引用;然后创建String Builder对象，StringBuilder.toString 方法创建的对象，返回String对象引用。一共创建6个对象。
        String com1 = new String("m") +new String("q");
        String com2 = com1.intern();//此时，变量池中没有mq字符串，因此，将com1放入常量池，并返回com1地址。因此，com1和com2相同
        String com3 = "mq";//com2的时候，已经将com1放入了常量池，因此直接返回com1的引用地址
        String com3_1 = "m"+"q";
        System.out.println("com3_1==com3:"+(com3_1==com3));//true


        System.out.println("com1==com2:"+(com1==com2));//true
        System.out.println("com1==com3"+(com1==com3));//true
        System.out.println("com2==com3"+(com2==com3));//true


        //如果没有，则创建n,w字符，有则将字符串引用返回给对应newString 对象。创建一个新的String对象，对象的值位nw,返回对象引用
        String com1_1 = new String("n")+new String("w");
        String com2_1 = "nw";//此时常量池中没有nw字符串，因此添加到constant pool中，以及string table中，返回引用地址。
        String com2_2 = com2_1.intern();//返回nw的地址，和com2_1一致。和com1_1不同
        System.out.println("com1_1==com2_1:"+(com1_1==com2_1));//false
        System.out.println("com2_2==com2_1:"+(com2_2==com2_1));//true


        String com4 = new String("hello world");//堆中创建一个String对象。字符串常量池中创建一个"hello world"。一共产生两个对象
        String com5 = com4.intern();//字符串常量池中已经有，指向常量池字符串地址。
        String com6 = "hello world";//已经有了这个字符串，因此和com5一致
//        首先在堆中创建String对象，s1指向这个对象。其次将字符添加到constant pool中，然后自动调用intern方法，在string table中添加一个引用，并返回引用地址给String对象，此时的s1指向堆中的对象地址。
        System.out.println("com4 == com5:"+(com4 == com5));//false
        System.out.println("com6 == com5:"+(com6 == com5));//true
        System.out.println("com4==com6:"+(com4==com6));//false


        String com7 = new String("12")+new String("3");//创建两个new 的String对象，创建一个String对象"12",一个String对象"3"，一个String Builder对象，以及一个新的String对象"123"存储在堆内存中。
        String com8 = com7.intern();//字符串常量池中没有123变量，因此将这个对象放入常量池中，并返回com7的引用地址。因此com8和com7相同

        //已经有了12和3字符串，只需要创建两个newString 对象然后引入字符即可，String Builder对象，获得一个新的String对象，保存常量池中的123，因此指向堆中的String对象，引入字符串池123，和谁都不一样
        String com9 = new String("12")+new String("3");
        String com10 = com9.intern();//常量池中有123，但是引用的是com7的地址，因此com10和com7相同。和com9不同。
        String com11 = "123";//和com7一样
        System.out.println("com7==com8:"+(com7==com8));//true
        System.out.println("com9==com8:"+(com9==com8));//false
        System.out.println("com9==com10:"+(com9==com10));//false
        System.out.println("com8==com10:"+(com8==com10));//true
        System.out.println("com11==com9:"+(com11==com9));//false
        System.out.println("com11==com7:"+(com11==com7));//true



//        直接定义的字面量，所以直接在constant pool中添加字符串"ab"，调用intern方法，添加到string pool中，并返回引用地址给s4。
        String s4 = "ab";
        String s5 = new String("ab");//创建一个String对象，s5指向这个对象，然后，检查string pool中有这个字符，则返回引用地址给对象。
        System.out.println("s4==s5:"+(s4==s5));//false

        String s6 = "c";
        String s7 = "d";
//        String cdpre = "ad";//比较之后把这个注释了
        String cd = "a"+"d";
        String cdaft = "ad";//把这个注释了比较
//        System.out.println("cd ==cdpre"+cd ==cdpre);//true 。因为constant pool中已经有ad，所以相等，并且由于是字面量拼接，因此无需String Builder对象。反编译文件中直接创建没有拼接过程
        System.out.println("cd==cdaft:"+ (cd==cdaft));//true  这个是因为由于"a"+"d"的过程中已经创建了，所以cdaft直接返回对应引用地址

        String s6_1 = "e";
        String s7_1 = "f";
//        String cdpre = "ad";//比较之后把这个注释了
        String ef_1 = s6_1+s7_1;
        //先在constant pool中创建字符"e","f",然后添加到string pool中，然后返回引用地址，由于是引用相加，因此创建String Builder对象，
        // 然后加载append 方法完成字符串拼接，最后调用StringBuilder内部toString方法，在constant pool中创建"ef",添加到String pool中然后引用返回给创建的String对象。创建一个新的String对象。ef_1指向这个对象然后入栈。
        System.out.println("ef_1:"+ef_1);

        final String fs8 = "g";
        String s9 = "a"+fs8;//不会声明创建g，而是直接创建ag。

        String b = "b";
        String ab = new String("a") + b;//创建a，然后

    }
}
