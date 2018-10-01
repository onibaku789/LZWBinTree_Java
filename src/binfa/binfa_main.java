package binfa;

import java.io.*;

class binfa_main {
	public static void main(String[] args) {

		long time = java.lang.System.currentTimeMillis();

		try {
			if (args.length != 2) {
				System.err.println("Hibás argumentumszám");
				System.exit(-1);
			}

			FileReader inputStream = new FileReader("/home/attilav/asd");

			int j;
			boolean inComment = false;
			char i;
			binfa bt = new binfa();

			while ((j = inputStream.read()) != -1) {

				i = (char) j;

				if (i == '>') {
					inComment = true;
					continue;
				}

				if (i == '\n') {
					inComment = false;
					continue;
				}

				if (inComment)
					continue;

				if (i == 'N')
					continue;

				for (int k = 0; k < 8; k++) {

					if ((i & 0x80) == 0x80)
						bt.addChar('1');
					else
						bt.addChar('0');

					i <<= 1;
				}

			}

			inputStream.close();
			PrintWriter pw = new PrintWriter(new FileWriter("/home/attilav/asd2"));

			bt.Print(bt.get_root_node(), pw);
			pw.println("Mélység: " + bt.get_max_depth());
			pw.println("Átlag: " + bt.Mean());
			pw.println("Szórás: " + bt.Variance());

			pw.close();

		} catch (IOException e) {
		}

		long dTime = java.lang.System.currentTimeMillis() - time;

		System.out.println(dTime / 1000.0 + " s");
	}
}