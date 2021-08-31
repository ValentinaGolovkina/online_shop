package ru.valensiya.online_shop.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.valensiya.online_shop.services.ProductService;
import ru.valensiya.online_shop.soap.products.*;

import java.util.function.Function;
import java.util.stream.Collectors;


@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.valensiya.ru/online_shop/products";
    private final ProductService productService;

    /*
        Пример запроса: POST http://localhost:8080/shop/ws
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:f="http://www.valensiya.ru/online_shop/products">
    <soapenv:Header/>
    <soapenv:Body>
        <f:getProductByIdRequest>
            <f:id>4</f:id>
        </f:getProductByIdRequest>
    </soapenv:Body>
</soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.findById(request.getId()).map(functionEntityToSoap).get());
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8080/shop/ws
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.valensiya.ru/online_shop/products">
    <soapenv:Header/>
    <soapenv:Body>
        <f:getAllProductsRequest/>
    </soapenv:Body>
</soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList()).forEach(response.getProducts()::add);
        return response;
    }

    public static final Function<ru.valensiya.online_shop.model.Product, Product> functionEntityToSoap = se -> {
        ru.valensiya.online_shop.soap.products.Product p = new ru.valensiya.online_shop.soap.products.Product();
        p.setId(se.getId());
        p.setTitle(se.getTitle());
        p.setPrice(se.getPrice());
        p.setCategoryTitle(se.getCategory().getTitle());
        return p;
    };
}
