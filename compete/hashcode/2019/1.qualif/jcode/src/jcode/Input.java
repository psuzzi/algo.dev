package jcode;

import java.util.ArrayList;
import java.util.List;

public class Input extends Base {

	int minValue, maxValue;

	int N;
	List<Photo> photos = new ArrayList<>();

	public char type;

	public void read(String filename) {

		scan(filename, sc -> {

			N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				boolean isH = sc.next().equals("H");
				Photo photo = new Photo(i, isH);
				int nt = sc.nextInt();
				List<String> tags = new ArrayList<>();
				for (int j = 0; j < nt; j++) {
					tags.add(sc.next());
				}
				photo.tags.addAll(tags);
				photos.add(photo);
			}

		});

	}

}
