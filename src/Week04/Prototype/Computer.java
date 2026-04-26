package Prototype;

class ComputerTest {

    public static void main(String[] args) {
        Computer computer = new Computer(new Disk((int) Math.pow(15, 2)), new Ram(4), new Cpu(6, 8), new Gpu(6));
        Computer copy = computer.clone();
    }
}

interface ComputerProto extends Cloneable {
    Computer clone();

}

public class Computer implements ComputerProto {

    protected Disk disk;
    protected Ram ram;
    protected Cpu cpu;
    protected Gpu gpu;

    public Computer(Computer otherComputer) {
        this.ram=new Ram(otherComputer.ram);
        this.cpu=new Cpu(otherComputer.cpu);
        this.gpu=new Gpu(otherComputer.gpu);
        this.disk=new Disk(otherComputer.disk);
    }

    public Computer(Disk disk, Ram ram, Cpu cpu, Gpu gpu) {
        this.disk = disk;
        this.ram = ram;
        this.cpu = cpu;
        this.gpu = gpu;
    }

    @Override
    public Computer clone() {
        return new Computer(this);
    }
}

class Disk {
    int numberOfSectors;

    public Disk(Disk otherDisk) {
        this.numberOfSectors = otherDisk.numberOfSectors;
    }

    public Disk(int numberOfSectors) {
        this.numberOfSectors = numberOfSectors;
    }
}

class Ram {
    int[] card;

    public Ram(Ram otherRam) {
        if (otherRam != null) {
            int size = otherRam.card.length;
            this.card = new int[size];
            for (int i = 0; i < size; i++) {
                this.card[i]=otherRam.card[i];
            }
        }
    }

    public Ram(int number) {
        this.card = new int[3];
        for (int i = 0; i < number; i++) {
            card[i] = 8;
        }
    }
}


class Gpu {
    protected int memo;


    public Gpu(Gpu otherGpu) {
        this.memo=otherGpu.memo;
    }

    public Gpu(int memo) {
        this.memo = memo;
    }
}

class Cpu {
    int cash;
    int numberOfCores;
    Core[] cores;


    public Cpu(Cpu otherCpu) {
        this.cash=otherCpu.cash;
        this.numberOfCores=otherCpu.numberOfCores;
        if(otherCpu.cores!=null)
        {
            this.cores= new Core[this.numberOfCores];
            for (int i = 0; i < this.numberOfCores; i++) {
                this.cores[i]=new Core(otherCpu.cores[i]);
            }
        }


    }

    public Cpu(int cash, int numberOfCores) {
        this.cash = cash;
        this.numberOfCores = numberOfCores;

        this.cores = new Core[numberOfCores];
        for (int i = 0; i < numberOfCores; i++) {
            this.cores[i] = new Core();
        }
    }
}

class Core {
    public Core() {
    }
    public Core(Core otherCore) {
    }
}
