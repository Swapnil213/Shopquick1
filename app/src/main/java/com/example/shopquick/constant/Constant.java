package com.example.shopquick.constant;

import com.example.shopquick.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final List<Integer> QUANTITY_LIST=new ArrayList<>();

    static {
        for (int i = 1; i < 11; i++) {
            QUANTITY_LIST.add(i);
        }
    }

    public static final Product p1= new Product(1,"Samsung Galaxy M31", BigDecimal.valueOf(21990),"6.4 inches (16.26 cm) 1080x2380 px, 403 PPI Super AMOLED Display | Fingerprint Sensor\n" +
            "8 GB RAM | 128 GB ROM | Expandable Upto 512 GB\n" +
            "64 MP + 8 MP + 5 MP + 5 MP Quad Primary Cameras | 32 MP Front Camera\n" +
            "Octa core (2.3 GHz, Quad Core + 1.7 GHz, Quad core) Samsung Exynos 9 Octa Processor\n" +
            "6000 mAh Battery\n" +
            "Android v10 (Q) OS\n" +
            "1 Year Manufacturer Warranty","samsung_galaxy_m31");

    public static final Product p2= new Product(2,"Samsung Galaxy M52 5G",BigDecimal.valueOf(26999),"Triple camera setup-64MP (F 1.8) main camera + 12MP (F2.2) Ultra wide camera+ 5MP (F2.4) depth camera 32MP (F2.2) front camera\n" +
            "16.95 centimeters (6.7-inch) Super AMOLED Plus- Infinity O display, FHD+ resolution 1080 x 2400 (FHD+) pixels protected by Gorilla Glass 5\n" +
            "Qualcomm SDM 778G Octa Core 2.4GHz,1.8GHz Processor with the 11 band support for a True 5G experience\n" +
            "Monster 5000 mAh Battery | Memory, Storage & SIM: 6GB RAM | 128GB internal memory expandable up to 1TB| SIM 1 + Hybrid (SIM or MicroSD)","samsung_galaxy_m52");

    public static final Product p3= new Product(3,"Samsung Galaxy M42 5G",BigDecimal.valueOf(24999),"6.6 inches (16.77 cm) 265 PPI, HD+ Super AMOLED Infinity-U Display | Fingerprint Sensor\n" +
            "6 GB RAM | 128 GB ROM | Expandable Upto 1 TB\n" +
            "48 MP + 8 MP + 5 MP + 5 MP Quad Primary Cameras | 20 MP Front Camera\n" +
            "Octa core (2.2 GHz, Dual Core + 1.8 GHz, Hexa Core) Qualcomm Snapdragon 750G Processor\n" +
            "5000 mAh Lithium-ion Battery\n" +
            "Android v11 OS\n" +
            "1 Year Manufacturer Warranty","samsung_galaxy_m42");

    public static final List<Product> PRODUCT_LIST=new ArrayList<Product>();

    static{
        PRODUCT_LIST.add(p1);
        PRODUCT_LIST.add(p2);
        PRODUCT_LIST.add(p3);
    }

    public static final String CURRENCY = "₹";
}
