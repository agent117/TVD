package de.agent117.TVD.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.configuration.file.YamlConfiguration;

public class test {
	public static void main(String[] args) throws IOException {
		
		for(int i = 0; i < (24 / 1); i++) {
			System.out.println(i++);
			System.out.println(i);
		}
		
		
	}
	
	
	public static void loadFiles() throws IOException {
		InputStream steam = main.class.getResourceAsStream("/" + "config.yml");
		File f = new File("test.yml");
		
		Scanner s = new Scanner(steam);
		FileWriter fw = new FileWriter(f);

		BufferedWriter wr = new BufferedWriter(fw);
		
		while(s.hasNextLine()) {
			String line = s.nextLine() + "\n";
			wr.write(line);
		}
		

		wr.close();
		fw.close();
		s.close();
		
	}
	
}
