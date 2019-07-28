
/**
 * @author Tara G.
 */

import edu.duke.FileResource;

public class HelloWorldManyLang {
	public static void main(String[] args) {
		HelloWorldManyLang hello = new HelloWorldManyLang();
		hello.runHello();
	}
	public void runHello () {
		FileResource res = new FileResource("hello_unicode.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
}
