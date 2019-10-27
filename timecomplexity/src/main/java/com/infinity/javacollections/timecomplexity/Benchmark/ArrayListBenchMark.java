package com.infinity.javacollections.timecomplexity.Benchmark;

import com.infinity.javacollections.timecomplexity.domain.Employee;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 20)
public class ArrayListBenchMark {
    @State(Scope.Thread)
    public static class MyState{
        List<Employee> employeeList = new ArrayList<>();
        long iterations = 100000;
        Employee myEmployee = new Employee(100L, "Tony");
        int employeeIndex = -1;
        @Setup(Level.Trial)
        public void setUp(){
            for(long i=0; i<iterations; i++){
                employeeList.add(new Employee(i, "Thor"));
            }
            employeeList.add(myEmployee);
            employeeIndex = employeeList.indexOf(myEmployee);
        }
    }

    @Benchmark
    public void testAdd(ArrayListBenchMark.MyState state){
        state.employeeList.add(new Employee(state.iterations + 1, "Loki"));
    }

    @Benchmark
    public void testAddAtTheEnd(ArrayListBenchMark.MyState state){
        state.employeeList.add((int)state.iterations, new Employee(state.iterations, "Loki"));
    }

    @Benchmark
    public void testAddAtTheBeggining(ArrayListBenchMark.MyState state){
        state.employeeList.add(0, new Employee(state.iterations, "Loki"));
    }

    @Benchmark
    public Employee testGet(ArrayListBenchMark.MyState state){
        return state.employeeList.get(state.employeeIndex);
    }

    @Benchmark
    public boolean testContains(ArrayListBenchMark.MyState state){
        return state.employeeList.contains(state.myEmployee);
    }

    @Benchmark
    public int testIndexOf(ArrayListBenchMark.MyState state){
        return state.employeeList.indexOf(state.myEmployee);
    }

    @Benchmark
    public boolean testRemove(ArrayListBenchMark.MyState state){
        return state.employeeList.remove(state.myEmployee);
    }

    public static void main(String[] args) throws RunnerException {
        System.gc();
        Options options = new OptionsBuilder()
                .include(ArrayListBenchMark.class.getSimpleName()).threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server")
                .build();
        new Runner(options).run();
    }
}
