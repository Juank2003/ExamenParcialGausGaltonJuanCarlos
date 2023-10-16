package org.example;



import java.util.concurrent.BlockingQueue;
class AssemblyLine implements Runnable {
    public BlockingQueue<Integer> buffer;
    public Visualizer visualizer;

    public AssemblyLine(BlockingQueue<Integer> buffer, Visualizer visualizer) {
        this.buffer = buffer;
        this.visualizer = visualizer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Retira un componente del búfer compartido
                int component = buffer.take();

                // Simula el ensamblaje de la máquina
                assembleMachine(component);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void assembleMachine(int component) {
        // Simula el ensamblaje de la máquina
        System.out.println("Ensamblaje de componente " + component + " en " + Thread.currentThread().getName());
        // Implementa tu lógica de ensamblaje aquí
        // Por ejemplo, puedes simular el tiempo que lleva el ensamblaje con una pausa.
        try {
            Thread.sleep(100); // Simula un tiempo de ensamblaje de 100 milisegundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Ensamblaje de componente " + component + " completado en " + Thread.currentThread().getName());
    }
}
