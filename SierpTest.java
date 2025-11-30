/**
 * 测试类：演示如何使用Sierp类生成谢尔宾斯基三角
 * 
 * 使用方法：
 * javac Sierp.java SierpTest.java
 * java SierpTest
 */
public class SierpTest {
    public static void main(String[] args) {
        // 创建Sierp对象，使用颜色填充模式
        Sierp sierp = new Sierp("colorFilling", 0.01);
        
        // 设置画布
        sierp.setupCanvas(800);
        
        // 绘制谢尔宾斯基三角
        sierp.tri();
        
        System.out.println("谢尔宾斯基三角已绘制完成！");
        System.out.println("可以尝试修改stopSize参数来获得不同的细节层次。");
    }
}

