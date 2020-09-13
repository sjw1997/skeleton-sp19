import edu.princeton.cs.algs4.StdAudio;
import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {

    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static int keyIndex(char key) {
        for (int i = 0; i < keyboard.length(); i++) {
            if (keyboard.charAt(i) == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12);
            strings[i] = new GuitarString(frequency);
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyIndex(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            double sample = .0;
            for (GuitarString g: strings) {
                sample += g.sample();
            }

            StdAudio.play(sample);

            for (GuitarString g: strings) {
                g.tic();
            }
        }
    }
}

