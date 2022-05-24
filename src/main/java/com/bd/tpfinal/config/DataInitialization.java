package com.bd.tpfinal.config;


import com.bd.tpfinal.model.*;
import com.bd.tpfinal.repositories.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@Transactional
public class DataInitialization implements ApplicationRunner {
    private final OrderRepository orderRepository;
    private final SupplierRepository supplierRepository;
    private final ClientRepository clientRepository;
    private final SupplierTypeRepository supplierTypeRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductRepository productRepository;
    private final HistoricalProductPriceRepository historicalProductPriceRepository;
    private final ItemRepository itemRepository;

    private List<Long> clients = new ArrayList<>();
    private List<Long> orders = new ArrayList<>();
    private List<Long> suppliers = new ArrayList<>();
    private List<ProductType> productTypes = new ArrayList<>();
    private List<Long> products = new ArrayList<>();

    private Random random = new Random();
    private final DeliveryManRepository deliveryManRepository;

    @Value("${data.initialization.number_of_orders:100}")
    private int numberOfOrders;
    @Value("${data.initialization.number_of_clients:100}")
    private int numberOfClients;
    @Value("${data.initialization.number_of_products:200}")
    private int numberOfProducts;
    @Value("${data.initialization.number_of_delivery:10}")
    private int numberOfDelivery;


    public DataInitialization(OrderRepository orderRepository,
                              SupplierRepository supplierRepository,
                              ClientRepository clientRepository,
                              SupplierTypeRepository supplierTypeRepository,
                              ProductTypeRepository productTypeRepository,
                              ProductRepository productRepository,
                              HistoricalProductPriceRepository historicalProductPriceRepository,
                              ItemRepository itemRepository, DeliveryManRepository deliveryManRepository) {
        this.orderRepository = orderRepository;
        this.supplierRepository = supplierRepository;
        this.clientRepository = clientRepository;
        this.supplierTypeRepository = supplierTypeRepository;
        this.productTypeRepository = productTypeRepository;
        this.productRepository = productRepository;
        this.historicalProductPriceRepository = historicalProductPriceRepository;
        this.itemRepository = itemRepository;
        this.deliveryManRepository = deliveryManRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (orderRepository.count() == 0) {
            generateClients();
            generateSuppliers();
            generateProducts();
            generateOrders();
            addItemsToOrders();
            generateDeliveryMen();
            generateSupplierWithAllProductTypes();
        }
    }
    private void generateOrders(){
        if (clients.size() == 0)
            clients = clientRepository.findAll().stream().map(c -> c.getId()).collect(Collectors.toList());
        for (int i = 0; i < numberOfOrders; i++) {
            Order order = new Order();
            order.setNumber(random.nextInt(100000));
            order.setDateOfOrder(new Date());
            order.setTotalPrice(0f);
            Pending status = new Pending();
            status.setName();
            status.setOrder(order);
            order.setStatus(status);

            Client client = clientRepository.findById(clients.get(random.nextInt(clients.size()))).get();
            client.addOrder(order);

            order.setClient(client);
            if (client.getAddresses().size() > 0)
                order.setAddress(client.getAddress(0));
            orders.add(orderRepository.save(order).getId());
        }
    }

    private void generateClients(){
        for (int i = 0; i < numberOfClients; i++) {
            Client client = new Client();
            client.setName(Datasets.LASTNAMES[random.nextInt(100)] + " " + Datasets.Names[random.nextInt(100)]);
            client.setDateOfRegister(new Date());
            client.setDateOfBirth(new Date());
            client.setUsername(client.getName().replace(" ", "").toLowerCase(Locale.ROOT));
            client.setPassword("");

            int j = random.nextInt(5) + 1;
            int x = 0;
            do {
                String street = Datasets.STREETS[random.nextInt(Datasets.STREETS.length)]+ " Nro. "+ (random.nextInt(5000) + 300) + " - Buenos Aires";
                Address address = new Address();
                address.setAddress(street);
                address.setClient(client);
                address.setName(client.getName());
                client.add(address);
                x++;
            } while (x < j);

            clients.add(clientRepository.save(client).getId());
        }
    }

    private void generateSuppliers(){
        List<SupplierType> supplierTypes = new ArrayList<>();
        for(String type : Datasets.SUPPLIER_TYPES){
            SupplierType supplierType = new SupplierType();
            supplierType.setName(type);
            supplierType = supplierTypeRepository.save(supplierType);
            supplierTypes.add(supplierType);
        }



        for (String company : Arrays.asList(Datasets.COMPANY_NAMES).subList(0,20)) {
            Supplier supplier = new Supplier();
            supplier.setName(company);
            supplier.setCuil(30 + "-" + (20000000 + random.nextInt(20000000)) + "-" + random.nextInt(9));

            SupplierType supplierType = supplierTypes.get(random.nextInt(supplierTypes.size()));

            supplier.setType(supplierType);
            supplierType.add(supplier);
            supplier = supplierRepository.save(supplier);

            suppliers.add(supplier.getId());
        }
    }

    public void generateProducts(){
        for (String type: Datasets.PRODUCT_TYPES){
            ProductType productType = new ProductType();
            productType.setName(type);
            productType = productTypeRepository.save(productType);
            productTypes.add(productType);
        }

        int supLength = suppliers.size();
        int npLength = Datasets.PRODUCT_NAME_PARTS.length;

        for (int i = 0; i< numberOfProducts; i++){
            float price = (float)Math.random() * random.nextInt(1000);
            String name = Datasets.PRODUCT_NAME_PARTS[random.nextInt(npLength)] + Datasets.PRODUCT_NAME_PARTS[random.nextInt(npLength)] +
                    Datasets.PRODUCT_NAME_PARTS[random.nextInt(npLength)];
            int pti = random.nextInt(productTypes.size());
            ProductType type = productTypes.get(pti);

            Product product = new Product();
            product.setName(name);
            product.setType(type);
            product.setPrice(price);

            HistoricalProductPrice historicalPrice = new HistoricalProductPrice();
            historicalPrice.setPrice(price);
            historicalPrice.setStartDate(new Date());
            historicalPrice.setProduct(product);

            product.addPrice(historicalPrice);

            Supplier supplier = supplierRepository.findById(suppliers.get(random.nextInt(suppliers.size()))).get();
            supplier.getProducts().size();
            type.addProduct(product);
            product.setSupplier(supplier);
            supplier.addProduct(product);

            product = productRepository.save(product);

            products.add(product.getId());
        }

    }
    public void addItemsToOrders(){
        for (Long orderId: orders) {
            int j = random.nextInt(15) + 1;
            Order order = orderRepository.findById(orderId).get();
            for(int i = 0; i < j; i++){
                Item item = new Item();
                item.setQuantity(random.nextInt(10)+1);
                item.setOrder(order);
                Product product = productRepository.findById(products.get(random.nextInt(products.size()))).get();
                item.setProduct(product);
                order.setTotalPrice(order.getTotalPrice() + (item.getQuantity() * item.getProduct().getPrice()));

                order.addItem(item);
            }
            orderRepository.save(order);

        }
    }

    public void generateSupplierWithAllProductTypes(){

        Supplier supplier = new Supplier();
        supplier.setName("Company with all product types");
        supplier.setCuil(30 + "-" + (20000000 + random.nextInt(20000000)) + "-" + random.nextInt(9));

        SupplierType supplierType = supplierTypeRepository.findById(1l).get();
        supplier.setType(supplierType);
        supplier = supplierRepository.save(supplier);
        suppliers.add(supplier.getId());

        int supLength = suppliers.size();
        int npLength = Datasets.PRODUCT_NAME_PARTS.length;

        productTypes = productTypeRepository.findAll();

        for (ProductType type: productTypes){

            float price = (float)Math.random() * random.nextInt(1000);
            String name = Datasets.PRODUCT_NAME_PARTS[random.nextInt(npLength)] + Datasets.PRODUCT_NAME_PARTS[random.nextInt(npLength)] +
                    Datasets.PRODUCT_NAME_PARTS[random.nextInt(npLength)];
            int pti = random.nextInt(productTypes.size());

            Product product = new Product();
            product.setName(name);
            product.setType(type);
            product.setPrice(price);

            HistoricalProductPrice historicalPrice = new HistoricalProductPrice();
            historicalPrice.setPrice(price);
            historicalPrice.setStartDate(new Date());
            historicalPrice.setProduct(product);

            product.addPrice(historicalPrice);
            supplier.addProduct(product);
            product.setSupplier(supplier);

            product = productRepository.save(product);

            products.add(product.getId());
        }

    }

    public void generateDeliveryMen(){
        for (int i = 0; i < numberOfDelivery; i++){
            DeliveryMan deliveryMan = new DeliveryMan();
            deliveryMan.setName(Datasets.LASTNAMES[random.nextInt(Datasets.LASTNAMES.length)] + " " + Datasets.Names[random.nextInt(Datasets.Names.length)]);
            deliveryMan.setDateOfAdmission(new Date());
            deliveryMan.setEmail(deliveryMan.getName().toLowerCase(Locale.ROOT).replace(" ","")+"@gmail.com");
            deliveryMan.setPassword(deliveryMan.getName().toLowerCase(Locale.ROOT).replace("a","4"));
            deliveryMan.setUsername(deliveryMan.getName().toLowerCase(Locale.ROOT).replace(" ","_"));
            deliveryMan.setNumberOfSuccessOrders(0);
            deliveryMan.setDateOfBirth(new Date());
            deliveryManRepository.save(deliveryMan);
        }
    }
}
