package com.bcam.bcmonitor.extractor.client;

import com.bcam.bcmonitor.extractor.mapper.RPCResponseException;
import com.bcam.bcmonitor.model.BitcoinBlock;
import com.bcam.bcmonitor.model.BitcoinTransaction;
import com.bcam.bcmonitor.model.TransactionPoolInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


/**
 * see http://www.mock-server.com/mock_server/creating_expectations.html
 * http://chainquery.com/bitcoin-api/getblockcount
 *
 * TODO run tests in parallel? http://www.mock-server.com/mock_server/running_tests_in_parallel.html
 */
public class BitcoinClientTest {
    private ClientAndServer mockServer;
    private String validBlockResponse;
    private String errorBlockResponse;
    private String getBlockHashResponse;
    private String getTransactionResponse;
    private String getBestBlockHashResponse;
    private String getTransactionPoolInfoResponse;

    private String userName = "bitcoinrpc";
    private String password = "123";
    private String hostName = "localhost";
    private int port = 28332;


    public BitcoinClientTest() {
        validBlockResponse = """
                {
                	"result": {
                		"hash": "00000000839a8e6886ab5951d76f411475428afc90947ee320161bbf18eb6048",
                		"confirmations": 527114,
                		"strippedsize": 215,
                		"size": 215,
                		"weight": 860,
                		"height": 1,
                		"version": 1,
                		"versionHex": "00000001",
                		"merkleroot": "0e3e2357e806b6cdb1f70b54c3a3a17b6714ee1f0e68bebb44a74b1efd512098",
                		"tx": [
                			"0e3e2357e806b6cdb1f70b54c3a3a17b6714ee1f0e68bebb44a74b1efd512098"
                		],
                		"time": 1231469665,
                		"mediantime": 1231469665,
                		"nonce": 2573394689,
                		"bits": "1d00ffff",
                		"difficulty": 1,
                		"chainwork": "0000000000000000000000000000000000000000000000000000000200020002",
                		"previousblockhash": "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f",
                		"nextblockhash": "000000006a625f06636b8bb6ac7b960a8d03705d1ace08b1a19da3fdcc99ddbd"
                	},
                	"error": null,
                	"id": null
                }\
                """;

        errorBlockResponse = """
                {
                	"result": null,
                	"error": {
                		"code": -5,
                		"message": "Block not found"
                	},
                	"id": null
                }\
                """;

        getBlockHashResponse = """
                {
                	"result": "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f",
                	"error": null,
                	"id": null
                }\
                """;

        getTransactionResponse = """
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

        getBestBlockHashResponse = """
                {
                	"result": "00000000000000000024c244f9c7d1cc0e593a7a4aa31c1ee2ef35206934bfff",
                	"error": null,
                	"id": null
                }\
                """;

        getTransactionPoolInfoResponse = """
                {
                	"result": {
                		"size": 13808,
                		"bytes": 10997650,
                		"usage": 34191136,
                		"maxmempool": 50000000,
                		"mempoolminfee": 0.00001000,
                		"minrelaytxfee": 0.00001000
                	},
                	"error": null,
                	"id": null
                }\
                """;
    }


    @BeforeEach
    public void startServer() {
        mockServer = startClientAndServer(28332);
    }

    @AfterEach
    public void stopServer() {
        mockServer.stop();
    }

    //TODO could use .json() instead of .withBody()
    @Test
    public void validRequestBlock() throws IOException {
        mockServer
                .when(
                        request()
                                .withMethod("POST")
                                .withBody("{\"jsonrpc\":\"jsonrpc\",\"id\":\"optional_string\",\"method\":\"getblock\",\"params\":[\"hash\",2]}")
                )
                .respond(
                        response()
                                .withBody(validBlockResponse)
                );

        BitcoinClient bc = new BitcoinClient(userName, password, hostName, port);

        BitcoinBlock b = bc.getBlock("hash");
        assertEquals("00000000839a8e6886ab5951d76f411475428afc90947ee320161bbf18eb6048", b.getHash());

    }

    /**
     * Strategy: send getblock request without specifying the block hash
     *
     */
    @Test
    public void invalidRequestBlock() {
        assertThrows(RPCResponseException.class, () -> {
            mockServer
                    .when(
                            request()
                                    .withMethod("POST")
                                    .withBody("{\"jsonrpc\":\"jsonrpc\",\"id\":\"optional_string\",\"method\":\"getblock\",\"params\":[\"\",2]}")
                    )
                    .respond(
                            response()
                                    .withBody(errorBlockResponse)
                    );

            BitcoinClient bc = new BitcoinClient(userName, password, hostName, port);
            bc.getBlock("");
        });
    }


    @Test
    public void getBlockHash() throws IOException {
        String testHash = "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f";
        mockServer
                .when(request()
                        .withMethod("POST")
                        .withBody("{\"jsonrpc\":\"jsonrpc\",\"id\":\"optional_string\",\"method\":\"getblockhash\",\"params\":[1]}")
                )
                .respond(
                        response()
                                .withBody(getBlockHashResponse)
                );

        BitcoinClient bc = new BitcoinClient(userName, password, hostName, port);
        String response = bc.getBlockHash(1);
        assertEquals(testHash, response);
    }

    @Test
    public void getTransaction() throws IOException {
        mockServer
                .when(request()
                        .withMethod("POST")
                        .withBody("{\"jsonrpc\":\"jsonrpc\",\"id\":\"optional_string\",\"method\":\"getrawtransaction\",\"params\":[\"4a4373359f0d2f423d40d9124b100a0917ded551351d7d90e79f5cef90404194\",true]}") //TODO check bool encoding
                )
                .respond(
                        response()
                                .withBody(getTransactionResponse)
                );

        BitcoinClient bc = new BitcoinClient(userName, password, hostName, port);
        BitcoinTransaction tc = bc.getTransaction("4a4373359f0d2f423d40d9124b100a0917ded551351d7d90e79f5cef90404194");
        assertEquals("4a4373359f0d2f423d40d9124b100a0917ded551351d7d90e79f5cef90404194", tc.getHash());
    }

    @Test
    public void getBestBlockHash() throws IOException {
        mockServer
                .when(request()
                        .withMethod("POST")
                        .withBody("{\"jsonrpc\":\"jsonrpc\",\"id\":\"optional_string\",\"method\":\"getbestblockhash\",\"params\":[]}")
                )
                .respond(
                        response()
                                .withBody(getBestBlockHashResponse)
                );

        BitcoinClient bc = new BitcoinClient(userName, password, hostName, port);
        String response = bc.getBestBlockHash();
        assertEquals("00000000000000000024c244f9c7d1cc0e593a7a4aa31c1ee2ef35206934bfff", response);
    }

    @Test
    public void getTransactionPoolInfo() throws IOException {
        mockServer
                .when(request()
                        .withMethod("POST")
                        .withBody("{\"jsonrpc\":\"jsonrpc\",\"id\":\"optional_string\",\"method\":\"getmempoolinfo\",\"params\":[]}")
                )
                .respond(
                        response()
                                .withBody(getTransactionPoolInfoResponse)
                );

        BitcoinClient bc = new BitcoinClient(userName, password, hostName, port);
        TransactionPoolInfo response = bc.getTransactionPoolInfo();
        assertEquals(10997650, response.getSizeBytes());
    }



}