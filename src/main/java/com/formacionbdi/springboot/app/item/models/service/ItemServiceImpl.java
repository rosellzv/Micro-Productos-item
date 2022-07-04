package com.formacionbdi.springboot.app.item.models.service;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private RestTemplate clienteRest;
    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/productos", Producto[].class));
        return productos.stream().map(mapper -> new Item(mapper,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, int cantidad) {
        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id",id.toString());
        Producto producto = clienteRest.getForObject("http://localhost:8001/detalle/{id}",Producto.class,pathVariables);
        return new Item(producto,cantidad);
    }
}
