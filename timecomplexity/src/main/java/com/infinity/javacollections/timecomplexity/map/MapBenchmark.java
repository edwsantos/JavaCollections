package com.infinity.javacollections.timecomplexity.map;

import com.infinity.javacollections.timecomplexity.domain.Employee;
import com.infinity.javacollections.timecomplexity.list.ListBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 20)
public class MapBenchmark {

    @State(Scope.Thread)
    public static class MyState{
        Map<Long, Employee> employeeMap = new HashMap<>();
        //Map<Long, Employee> employeeMap = new LinkedHashMap<>();
        //Map<Long, Employee> employeeMap = new TreeMap<>();

        long iterations = 100000;
        Employee myEmployee = new Employee(100L, "Tony");
        int employeeIndex = -1;
        @Setup(Level.Trial)
        public void setUp(){
            for(long i=0; i<iterations; i++){
                employeeMap.put(i, new Employee(i, "Thor"));
            }
        }
    }


    @Benchmark
    public void testPut(MapBenchmark.MyState state){
        state.employeeMap.put(state.myEmployee.getId(), state.myEmployee);
    }

    @Benchmark
    public void testContainsKey(MapBenchmark.MyState state){
        state.employeeMap.containsKey(state.myEmployee.getId());
    }

    @Benchmark
    public void testGet(MapBenchmark.MyState state){
        state.employeeMap.get(state.myEmployee);
    }

    @Benchmark
    public void testRemove(MapBenchmark.MyState state){
        state.employeeMap.remove(state.myEmployee);
    }

    public static void main(String[] args) throws RunnerException {
        System.gc();
        Options options = new OptionsBuilder()
                .include(MapBenchmark.class.getSimpleName()).threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server")
                .build();
        new Runner(options).run();
    }
}
