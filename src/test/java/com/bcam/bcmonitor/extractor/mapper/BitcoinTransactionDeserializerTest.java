package com.bcam.bcmonitor.extractor.mapper;

import com.bcam.bcmonitor.model.BitcoinTransaction;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitcoinTransactionDeserializerTest {
    private String json;
    private ObjectMapper mapper;
    private SimpleModule module;


    @BeforeEach
    public void setUp() throws Exception {
        module = new SimpleModule("CustomBitcoinDeserializer", new Version(1, 0, 0, null, null, null));
        mapper = new ObjectMapper();

        json = """
                {
                	"result": {
                		"txid": "4a4373359f0d2f423d40d9124b100a0917ded551351d7d90e79f5cef90404194",
                		"hash": "4a4373359f0d2f423d40d9124b100a0917ded551351d7d90e79f5cef90404194",
                		"version": 1,
                		"size": 288,
                		"vsize": 288,
                		"locktime": 0,
                		"vin": [
                			{
                				"txid": "2a21805ff8cfc622a2a2eda95f13e64f427bfea64a9640d32bbbbb24cc800896",
                				"vout": 92,
                				"scriptSig": {
                					"asm": "30440220078f014fa2f66de9eb6eddd2dbfda03388e44fd88af8465a989cd5f0e2af855e022064d51636cc9caf1962df8bbcf40bfaeebe2b332c8a84d707c38d67483b4fa2ff[ALL] 04fcf07bb1222f7925f2b7cc15183a40443c578e62ea17100aa3b44ba66905c95d4980aec4cd2f6eb426d1b1ec45d76724f26901099416b9265b76ba67c8b0b73d",
                					"hex": "4730440220078f014fa2f66de9eb6eddd2dbfda03388e44fd88af8465a989cd5f0e2af855e022064d51636cc9caf1962df8bbcf40bfaeebe2b332c8a84d707c38d67483b4fa2ff014104fcf07bb1222f7925f2b7cc15183a40443c578e62ea17100aa3b44ba66905c95d4980aec4cd2f6eb426d1b1ec45d76724f26901099416b9265b76ba67c8b0b73d"
                				},
                				"sequence": 4294967295
                			}
                		],
                		"vout": [
                			{
                				"value": 0.00002184,
                				"n": 0,
                				"scriptPubKey": {
                					"asm": "OP_DUP OP_HASH160 fa0692278afe508514b5ffee8fe5e97732ce0669 OP_EQUALVERIFY OP_CHECKSIG",
                					"hex": "76a914fa0692278afe508514b5ffee8fe5e97732ce066988ac",
                					"reqSigs": 1,
                					"type": "pubkeyhash",
                					"addresses": [
                						"1Po1oWkD2LmodfkBYiAktwh76vkF93LKnh"
                					]
                				}
                			},
                			{
                				"value": 0.00000000,
                				"n": 1,
                				"scriptPubKey": {
                					"asm": "OP_RETURN 6f6d6e69000000000000001f000003ce475689c0",
                					"hex": "6a146f6d6e69000000000000001f000003ce475689c0",
                					"type": "nulldata"
                				}
                			},
                			{
                				"value": 0.00000546,
                				"n": 2,
                				"scriptPubKey": {
                					"asm": "OP_DUP OP_HASH160 6471a5235fd52ac5ce87df5e509a61bfe2af47bc OP_EQUALVERIFY OP_CHECKSIG",
                					"hex": "76a9146471a5235fd52ac5ce87df5e509a61bfe2af47bc88ac",
                					"reqSigs": 1,
                					"type": "pubkeyhash",
                					"addresses": [
                						"1AA6iP6hrZfYiacfzb3VS5JoyKeZZBEYRW"
                					]
                				}
                			}
                		],
                		"hex": "0100000001960880cc24bbbb2bd340964aa6fe7b424fe6135fa9eda2a222c6cff85f80212a5c0000008a4730440220078f014fa2f66de9eb6eddd2dbfda03388e44fd88af8465a989cd5f0e2af855e022064d51636cc9caf1962df8bbcf40bfaeebe2b332c8a84d707c38d67483b4fa2ff014104fcf07bb1222f7925f2b7cc15183a40443c578e62ea17100aa3b44ba66905c95d4980aec4cd2f6eb426d1b1ec45d76724f26901099416b9265b76ba67c8b0b73dffffffff0388080000000000001976a914fa0692278afe508514b5ffee8fe5e97732ce066988ac0000000000000000166a146f6d6e69000000000000001f000003ce475689c022020000000000001976a9146471a5235fd52ac5ce87df5e509a61bfe2af47bc88ac00000000",
                		"blockhash": "000000000000000000285a375f9d33e1782f3cff9cb1d4cfe1faf1356eb998fb",
                		"confirmations": 1,
                		"time": 1528836244,
                		"blocktime": 1528836244
                	},
                	"error": null,
                	"id": null
                }\
                """;
    }

    @Test
    public void deserialize() throws IOException {
        module.addDeserializer(BitcoinTransaction.class, new BitcoinTransactionDeserializer());
        mapper.registerModule(module);

        BitcoinTransaction t = mapper.readValue(json, BitcoinTransaction.class);

        // check hashes
        assertEquals("000000000000000000285a375f9d33e1782f3cff9cb1d4cfe1faf1356eb998fb", t.getBlockHash());
        assertEquals("4a4373359f0d2f423d40d9124b100a0917ded551351d7d90e79f5cef90404194", t.getHash());
        assertEquals(288, t.getSizeBytes());

        // check transactions
        assertEquals("2a21805ff8cfc622a2a2eda95f13e64f427bfea64a9640d32bbbbb24cc800896", t.getVin().getFirst().getTxid());
        assertEquals(92, t.getVin().getFirst().getVout());

        assertEquals(0.00002184F, t.getVout(0).getValue());

        assertEquals(0.00000546F, t.getVout(2).getValue());

        assertEquals(1, t.getVin().size());

        assertEquals(3, t.getVout().size());
    }
}