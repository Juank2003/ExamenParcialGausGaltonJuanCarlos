package org.example;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ProductionTask implements Runnable {
    private final BlockingQueue<Integer> buffer;
    private final Visualizer visualizer;
    private final int workstationId;
    private final Random random = new Random();

    public ProductionTask(int workstationId, BlockingQueue<Integer> buffer, Visualizer visualizer) {
        this.workstationId = workstationId;
        this.buffer = buffer;
        this.visualizer = visualizer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Simula la producción de componentes
                int component = produceComponent();

                // Coloca el componente en el búfer compartido
                buffer.put(component);

                // Muestra la distribución en tiempo real
                updateDistribution();

                // Agrega una pausa para simular la producción
                Thread.sleep(500); // Ajusta el tiempo según tus necesidades
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private int produceComponent() {
        // Simula la producción de componentes
        return random.nextInt(100);
    }

    private void updateDistribution() {
        // Aquí debes implementar la lógica para actualizar la distribución en el visualizador
        // Puedes usar visualizer.updateDistribution(currentDistribution) si es necesario
    }
}