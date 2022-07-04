package com.formacionbdi.springboot.app.item.models.service;

import com.formacionbdi.springboot.app.item.cliente.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeing")
@Primary
public class ItenServiceFeign implements ItemService{

    private ProductoClienteRest clienteFeing;
    @Override
    public List<Item> findAll() {
        return clienteFeing.listar().stream().map(mapper -> new Item(mapper,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, int cantidad) {
        return new Item(clienteFeing.detalle(id),cantidad );
    }
}
