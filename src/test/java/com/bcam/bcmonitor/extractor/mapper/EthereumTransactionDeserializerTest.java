package com.bcam.bcmonitor.extractor.mapper;

import com.bcam.bcmonitor.model.EthereumTransaction;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EthereumTransactionDeserializerTest {
    private String json;

    @BeforeEach
    public void setUp() throws Exception {
       json = """
               {
               "id":1,
               "jsonrpc":"2.0",
               "result": {
                   "hash":"0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b",
                   "nonce":"0x",
                   "blockHash": "0xbeab0aa2411b7ab17f30a99d3cb9c6ef2fc5426d6ad6fd9e2a26a6aed1d1055b",
                   "blockNumber": "0x15df", // 5599
                   "transactionIndex":  "0x1", // 1
                   "from":"0x407d73d8a49eeb85d32cf465507dd71d507100c1",
                   "to":"0x85h43d8a49eeb85d32cf465507dd71d507100c1",
                   "value":"0x7f110", // 520464
                   "gas": "0x7f110", // 520464
                   "gasPrice":"0x09184e72a000",
                   "input":"0x603880600c6000396000f300603880600c6000396000f3603880600c6000396000f360"
                 }
               }\
               """;
    }

    @Test
    public void deserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CustomBitcoinDeserializer", new Version(1, 0, 0, null, null, null));

        module.addDeserializer(EthereumTransaction.class, new EthereumTransactionDeserializer());
        mapper.registerModule(module);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        EthereumTransaction t = mapper.readValue(json, EthereumTransaction.class);

        // general
        assertEquals("0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b", t.getHash());
        assertEquals("0xbeab0aa2411b7ab17f30a99d3cb9c6ef2fc5426d6ad6fd9e2a26a6aed1d1055b", t.getBlockHash());

        // eth
        assertEquals("0x407d73d8a49eeb85d32cf465507dd71d507100c1", t.from());
        assertEquals("0x85h43d8a49eeb85d32cf465507dd71d507100c1", t.to());
        assertEquals(520464L, t.getValue());
        assertEquals(520464L, t.getGas());
        assertEquals(10000000000000L, t.getGasPrice());
    }
}