public class AnnotationTest {

    @SuppressWarnings(":descrecated")
    public static void main(String[] args) {
        sayHello();
    }

    @Deprecated
    public static void sayHello() {
        System.out.println("这是一个废弃的方法 ，helloworld");
    }
}
