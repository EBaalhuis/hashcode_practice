package hashcode_practice;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class main {

	// Input instances
//	public static String[] instances = { "Example", "Small", "Medium", "Big" };
	public static String[] instances = { "example", "small" };

	// variables that are read from input
	public static long totalScore;
	public static int nRows, nCols, req, maxCells;
	public static ArrayList<Slice> slices;
	public static Cell[][] cells;

	// variables for making output
	public static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		for (String s : instances) {
			String inputDir = "/home/erik/git/hashcode_practice/files/" + s + ".in";
			String outDir = "/home/erik/git/hashcode_practice/files/" + s + ".out";
			readInput(inputDir);
			initWriter(outDir);

			// Solution method here.
			sol1(outDir);
			writeOutput();
			
			bw.close();
			
			// Evaluate solution locally, if needed
			long result = evaluateSolution(outDir);
			if (!s.equals("example")) {
				totalScore += result;
			}
			System.err.printf("Result for instance %s: ", s);
			System.err.println(result);
		}

		System.err.printf("Total score: %d\n", totalScore);
	}

	public static void readInput(String fileDir) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileDir));
		String[] split;

		// Read input here
		split = br.readLine().split(" ");
		nRows = Integer.parseInt(split[0]);
		nCols = Integer.parseInt(split[1]);
		req = Integer.parseInt(split[2]);
		maxCells = Integer.parseInt(split[3]);
		
		cells = new Cell[nRows][nCols];
		for (int i = 0; i < nRows; i++) {
			String line = br.readLine();
			for (int j = 0; j < nCols; j++) {
				cells[i][j] = new Cell(j+i*nCols, i, j, line.charAt(j) == 'T');
			}
		}

		br.close();
	}
	
	// Cut all columns completely, and rows per maxCells
	public static void sol1(String fileDir) throws IOException {
		slices = new ArrayList<>();
		int _id = -1;
		Slice curSlice = new Slice(-1);
		for (int i = 0; i < nCols; i++) {
			for (int j = 0; j < nRows; j++) {
				if (j % maxCells == 0) {
					// Make new slice
					_id++;
					curSlice = new Slice(_id);
					slices.add(curSlice);
				}
				curSlice.addCell(cells[j][i]);
			}
		}
	}

	public static long evaluateSolution(String fileDir) {
		// Client-side evaluation of solution
		return 42;
	}
	
	public static void writeOutput() {
		int nSlices = 0;
		for (Slice s : slices) {
			if (s.isLegal(req, maxCells)) {
				nSlices++;
			}
		}
		writeLine(String.format("%d\n", nSlices));
		
		for (Slice s : slices) {
			if (s.isLegal(req, maxCells)) {
				writeLine(s.getLine());
			}
		}
	}
	
	public static void writeLine(String s) {
		try {
			bw.write(s);
			bw.write("\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void filePrepend(String line, String fileDir) throws IOException {
		String tempDir = "/home/erik/Hashcode2016/temp";
		Files.copy(Paths.get(fileDir), Paths.get(tempDir), StandardCopyOption.REPLACE_EXISTING);
		initWriter(fileDir);
		bw.write(line);
		BufferedReader br = new BufferedReader(new FileReader(tempDir));
		String tempLine = "";
		while ((tempLine = br.readLine()) != null) {
			bw.write(tempLine);
			bw.write("\n");
		}
		br.close();
		bw.close();
	}

	public static void initWriter(String fileDir) throws UnsupportedEncodingException, FileNotFoundException {
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "utf-8"));
	}


}