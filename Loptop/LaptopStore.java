import java.util.*;

class Laptop {
    private String brand;
    private int ram; // ОЗУ в ГБ
    private int storage; // Объем ЖД в ГБ
    private String os; // Операционная система
    private String color; // Цвет

    public Laptop(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class LaptopStore {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>(Arrays.asList(
            new Laptop("Dell", 16, 512, "Windows10", "Black"),
            new Laptop("HP", 8, 256, "Linux", "Silver"),
            new Laptop("Apple", 8, 512, "macOS", "Gray"),
            new Laptop("Asus", 16, 1024, "Windows11", "Purple"),
            new Laptop("Lenovo", 32, 1024, "Windows10", "White")
                
        ));

        filterLaptops(laptops);
    }

    public static void filterLaptops(Set<Laptop> laptops) {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<String, String> criteria = new HashMap<>();

            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ (минимальное значение)");
            System.out.println("2 - Объем ЖД (минимальное значение)");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("Введите критерии через пробел (например: 1 2):");

            String[] inputCriteria = scanner.nextLine().split(" ");
            for (String criterion : inputCriteria) {
                switch (criterion) {
                    case "1":
                        System.out.print("Введите минимальное значение ОЗУ (в ГБ): ");
                        criteria.put("ram", scanner.next());
                        break;
                    case "2":
                        System.out.print("Введите минимальное значение объема ЖД (в ГБ): ");
                        criteria.put("storage", scanner.next());
                        break;
                    case "3":
                        System.out.print("Введите операционную систему: ");
                        criteria.put("os", scanner.next());
                        break;
                    case "4":
                        System.out.print("Введите цвет: ");
                        criteria.put("color", scanner.next());
                        break;
                    default:
                        System.out.println("Некорректный критерий: " + criterion);
                }
            }

            Set<Laptop> filteredLaptops = filterByCriteria(laptops, criteria);
            if (filteredLaptops.isEmpty()) {
                System.out.println("Нет ноутбуков, соответствующих заданным критериям.");
            } else {
                System.out.println("Подходящие ноутбуки:");
                for (Laptop laptop : filteredLaptops) {
                    System.out.println(laptop);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: пожалуйста, введите корректное значение.");
        }
    }

    public static Set<Laptop> filterByCriteria(Set<Laptop> laptops, Map<String, String> criteria) {
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);
        for (Map.Entry<String, String> entry : criteria.entrySet()) {
            switch (entry.getKey()) {
                case "ram":
                    int minRam = Integer.parseInt(entry.getValue());
                    filteredLaptops.removeIf(laptop -> laptop.getRam() < minRam);
                    break;
                case "storage":
                    int minStorage = Integer.parseInt(entry.getValue());
                    filteredLaptops.removeIf(laptop -> laptop.getStorage() < minStorage);
                    break;
                case "os":
                    filteredLaptops.removeIf(laptop -> !laptop.getOs().equalsIgnoreCase(entry.getValue()));
                    break;
                case "color":
                    filteredLaptops.removeIf(laptop -> !laptop.getColor().equalsIgnoreCase(entry.getValue()));
                    break;
            }
        }
        return filteredLaptops;
    }
}
