package util;

import java.io.File;
import java.util.Scanner;
import java.util.function.Consumer;

public interface IO {

	/**
	 * Scan the file with the given path. For a null path scan System.in
	 * 
	 * @param path
	 * @param consumer
	 */
	public static void scan(String path, Consumer<Scanner> consumer) {
		try (Scanner sc = path == null ? new Scanner(System.in) : new Scanner(new File(path))) {
			consumer.accept(sc);
		} catch (Exception e) {
			throw new RuntimeException("Error scanning " + path, e);
		}
	}
	
	public static void scan(Class cl, String path, Consumer<Scanner> consumer) {
		try( Scanner sc = (path==null) ? new Scanner(System.in) : (cl==null) ? new Scanner(new File(path)) : 
			new Scanner(cl.getResourceAsStream(path))){
			consumer.accept(sc);
		} catch (Exception e) {
			throw new RuntimeException("Error scanning " + path, e);
		}
	}
}