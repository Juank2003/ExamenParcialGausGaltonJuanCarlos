package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FactorySimulator {
    public static void iniciar() {
        int bufferSize = 100;
        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(bufferSize);

        // Crea una instancia de Visualizer para la visualización
        Visualizer visualizer = new Visualizer();

        int numWorkstations = 5;
        ExecutorService executor = Executors.newFixedThreadPool(numWorkstations);

        // Crea una cola de tareas (cola de producción)
        BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(numWorkstations);

        // Genera tareas de producción y colócalas en la cola de tareas
        for (int i = 0; i < numWorkstations; i++) {
            taskQueue.offer(new ProductionTask(i, buffer, visualizer));
        }

        // Crea trabajadores (hilos) para las estaciones de trabajo
        for (int i = 0; i < numWorkstations; i++) {
            executor.execute(new Workstation("Workstation " + i, buffer, visualizer, taskQueue));
        }

        // Cerrar el ExecutorService después de haber agregado todas las tareas
        executor.shutdown();
    }
}