package com.sun.poi;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sun.entity.OrderList;
import com.sun.entity.OrderListOrig;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class CsvTest {

    @Test
    void csvTestWithHeaders() throws IOException {

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema orderListSchema = CsvSchema.emptySchema().withHeader();
        csvMapper.findAndRegisterModules();
        MappingIterator<OrderListOrig> orderList = csvMapper.readerFor(OrderListOrig.class)
                .with(orderListSchema)
                .readValues(new File("src/test/resources/orderlist.csv"));
        System.out.println(orderList.readAll());
    }

    @Test
    void csvTestWithDiffHeaders() throws IOException {

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema orderListSchema = CsvSchema.emptySchema().withHeader();
        csvMapper.findAndRegisterModules();
        MappingIterator<OrderList> orderList = csvMapper.readerFor(OrderList.class)
                .with(orderListSchema)
                .readValues(new File("src/test/resources/orderlist.csv"));
        System.out.println(orderList.readAll());
    }

    @Test
    void csvTestWithOutEntityHeaders() throws IOException {

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.findAndRegisterModules();

        CsvSchema schema = CsvSchema.builder().setSkipFirstDataRow(true)
                .addColumn("item")
                .addColumn("quantity")
                .addColumn("unitPrice")
                .addColumn("orderDate").build();

        MappingIterator<Object> objectMappingIterator = csvMapper.readerFor(String.class).with(schema)
                .readValues(new File("src/test/resources/orderlist.csv"));
        System.out.println(objectMappingIterator.readAll());
    }


}
