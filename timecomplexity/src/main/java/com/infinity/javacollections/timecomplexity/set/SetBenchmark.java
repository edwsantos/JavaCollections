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

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 20)
public class SetBenchmark {

    @State(Scope.Thread)
    public static class MyState{
        Set<Employee> employeeSet = new HashSet<>();
        //Set<Employee> employeeSet = new LinkedHashSet<>();
        //Set<Employee> employeeSet = new TreeSet<>();

        long iterations = 100000;
        Employee myEmployee = new Employee(100L, "Tony");
        int employeeIndex = -1;
        @Setup(Level.Trial)
        public void setUp(){
            for(long i=0; i<iterations; i++){
                employeeSet.add(new Employee(i, "Thor"));
            }
        }
    }


    @Benchmark
    public void testAdd(SetBenchmark.MyState state){
        state.employeeSet.add(state.myEmployee);
    }

    @Benchmark
    public void testContainsKey(SetBenchmark.MyState state){
        state.employeeSet.contains(state.myEmployee);
    }

    @Benchmark
    public void testRemove(SetBenchmark.MyState state){
        state.employeeSet.remove(state.myEmployee);
    }

    public static void main(String[] args) throws RunnerException {
        System.gc();
        Options options = new OptionsBuilder()
                .include(SetBenchmark.class.getSimpleName()).threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server")
                .build();
        new Runner(options).run();
    }
}
