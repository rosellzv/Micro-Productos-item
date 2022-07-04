package com.formacionbdi.springboot.app.item.cliente;


import com.formacionbdi.springboot.app.item.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "servicio-productos",url = "localhost:8001")
public interface ProductoClienteRest {

    @GetMapping("/productos")
    public List<Producto> listar();

    @GetMapping("/detalle/{id}")
    public Producto detalle(@PathVariable Long id);
}
