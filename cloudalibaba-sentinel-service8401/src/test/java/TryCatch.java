import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class TryCatch {

    public static void main(String[] args) {
        try {
            System.out.println("hello");
            int a=10/0;
        } catch (Exception e) {
            e.printStackTrace();
            say();
        } finally {
        }
    }

    public static void say(){
        System.out.println("say");
    }
}
