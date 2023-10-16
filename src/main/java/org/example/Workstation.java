package org.example;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

class Workstation implements Runnable {
    private final BlockingQueue<Runnable> taskQueue;
    private BlockingQueue<Integer> buffer;
    private String name;
    private Random random = new Random();
    private Visualizer visualizer;

    public Workstation(String name, BlockingQueue<Integer> buffer, Visualizer visualizer, BlockingQueue<Runnable> taskQueue) {
        this.name = name;
        this.buffer = buffer;
        this.visualizer = visualizer;
        this.taskQueue = taskQueue;
    }


    @Override
    public void run() {
        while (true) {
            try {
                // Simula la producción de componentes
                int component = produceComponent();

                // Obtiene un array de objetos
                Object[] objects = buffer.toArray();

                if (objects.length > 0) {
                    int[] currentDistribution = new int[100];
                    for (int i = 0; i < currentDistribution.length; i++) {
                        currentDistribution[i] = (Integer) objects[i]; // Mapeo a int (autounboxing)
                    }

                    // Coloca el componente en el búfer compartido
                    buffer.put(component);

                    // Muestra la copia de la distribución en tiempo real
                    visualizer.updateDistribution(currentDistribution);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private int produceComponent() {
        // Simula la producción de componentes
        return random.nextInt(100);
    }
}