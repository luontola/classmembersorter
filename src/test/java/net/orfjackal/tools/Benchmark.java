/*
 * Class Member Sorter
 * Copyright (c) 2008 Esko Luontola, www.orfjackal.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.orfjackal.tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Esko Luontola
 * @since 30.1.2008
 */
public class Benchmark {

    private final int measureRounds;
    private final int minimumDurationMs;
    private final List<Result> results = new ArrayList<Result>();

    public Benchmark() {
        this(5, 500);
    }

    public Benchmark(int measureRounds, int minimumDurationMs) {
        this.measureRounds = measureRounds;
        this.minimumDurationMs = minimumDurationMs;
    }

    public Result runBenchmark(String description, Runnable benchmark) {
        int repeats = autoRepeatCount(benchmark, minimumDurationMs);
        long[] durations = finalMeasurements(benchmark, repeats, measureRounds);
        Result result = new Result(description, repeats, durations);
        results.add(result);
        return result;
    }

    private static int autoRepeatCount(Runnable benchmark, int minimumDurationMs) {
        int repeats = 1;
        long durationMs = 0;
        while (durationMs < minimumDurationMs) {
            long start = System.currentTimeMillis();
            repeat(repeats, benchmark);
            long end = System.currentTimeMillis();

            durationMs = end - start;
            if (durationMs < minimumDurationMs) {
                repeats = repeats * 2;
            }
        }
        return repeats;
    }

    private static long[] finalMeasurements(Runnable benchmark, int repeats, int measureRounds) {
        long[] durations = new long[measureRounds];
        for (int i = 0; i < durations.length; i++) {
            long start = System.currentTimeMillis();
            repeat(repeats, benchmark);
            long end = System.currentTimeMillis();
            durations[i] = end - start;
        }
        return durations;
    }

    private static void repeat(int repeats, Runnable benchmark) {
        for (int i = 0; i < repeats; i++) {
            benchmark.run();
        }
    }

    public void printResults() {
        DecimalFormat nf = getNumberFormat();
        int indexMaxLength = (results.size() + 1) / 10 + 1;
        int descMaxLength = 0;
        int nanosMaxLength = 0;
        for (Result result : results) {
            descMaxLength = Math.max(descMaxLength, result.getDescription().length());
            nanosMaxLength = Math.max(nanosMaxLength, nf.format(result.getMaxNanos()).length());
        }
        System.out.println("Benchmark Results");
        for (int i = 0; i < results.size(); i++) {
            Result result = results.get(i);
            String index = pad(i + 1, -indexMaxLength, " ");
            String desc = pad(result.getDescription(), descMaxLength, " ");
            String nanos = pad(nf.format(result.getNanos()), -nanosMaxLength, " ");
            String minNanos = pad(nf.format(result.getMinNanos()), -nanosMaxLength, " ");
            String maxNanos = pad(nf.format(result.getMaxNanos()), -nanosMaxLength, " ");
            System.out.println(index + ": " + desc + "    " + nanos + " ns"
                    + "    (min " + minNanos + " ns, max " + maxNanos + " ns)");
        }
    }

    private static DecimalFormat getNumberFormat() {
        return new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    }

    private static String pad(Object str, int padlen, String pad) {
        String padding = "";
        int len = Math.abs(padlen) - str.toString().length();
        if (len < 1) {
            return str.toString();
        }
        for (int i = 0; i < len; ++i) {
            padding = padding + pad;
        }
        return (padlen < 0 ? padding + str : str + padding);
    }

    public static class Result {

        private static final int MILLIS_TO_NANOS = 1000 * 1000;

        private final String description;
        private final int repeats;
        private final long[] totalDurationsMs;

        public Result(String description, int repeats, long[] totalDurationsMs) {
            this.description = description;
            this.repeats = repeats;
            this.totalDurationsMs = Arrays.copyOf(totalDurationsMs, totalDurationsMs.length);
            Arrays.sort(this.totalDurationsMs);
        }

        public String getDescription() {
            return description;
        }

        public int getRepeats() {
            return repeats;
        }

        public long getTotalMillis() {
            return median(totalDurationsMs);
        }

        public double getNanos() {
            return actualNanos(median(totalDurationsMs));
        }

        public double getMinNanos() {
            return actualNanos(min(totalDurationsMs));
        }

        public double getMaxNanos() {
            return actualNanos(max(totalDurationsMs));
        }

        private double actualNanos(long millis) {
            return millis * ((double) MILLIS_TO_NANOS / (double) repeats);
        }

        private static long median(long[] longs) {
            return longs[longs.length / 2];
        }

        private static long min(long[] longs) {
            return longs[0];
        }

        private static long max(long[] longs) {
            return longs[longs.length - 1];
        }

        public String toString() {
            NumberFormat nf = getNumberFormat();
            return "Benchmark.Result[" + getDescription() + ": " + nf.format(getNanos()) + " ns ("
                    + getRepeats() + " repeats, " + getTotalMillis() + " ms)]";
        }
    }
}
