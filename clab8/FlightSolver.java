import java.util.*;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    private PriorityQueue<Flight> heap;
    private static Comparator<Flight> flightComparator = (i, j) -> {
        return i.startTime() - j.startTime();
    };

    public FlightSolver(ArrayList<Flight> flights) {
        int n = flights.size();
        heap = new PriorityQueue<>(n, flightComparator);
        for (Flight flight: flights) {
            heap.add(flight);
        }
    }

    public int solve() {
        Queue<Flight> queue = new LinkedList<>();
        int max = 0;
        int current = 0;
        while (!heap.isEmpty()) {
            Flight last = heap.poll();
            current += last.passengers();
            queue.add(last);
            while (queue.peek().endTime() < last.startTime()) {
                current -= queue.poll().passengers();
            }
            max = Math.max(current, max);
        }
        return max;
    }

    public static void main(String[] args) {
        Flight f1 = new Flight(1, 2, 1);
        Flight f2 = new Flight(1, 2, 2);
        PriorityQueue<Flight> queue = new PriorityQueue<>(flightComparator);
        queue.add(f2);
        queue.add(f1);
        System.out.println(queue.peek().passengers());
    }

}
