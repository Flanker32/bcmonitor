package com.bcam.bcmonitor;

public class BitcoinRPCResponses {

    public static final String validBlockResponse = """
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

    public static final String errorBlockResponse = """
            {
            	"result": null,
            	"error": {
            		"code": -5,
            		"message": "Block not found"
            	},
            	"id": null
            }\
            """;

    public static final String getBlockHashResponse = """
            {
            	"result": "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f",
            	"error": null,
            	"id": null
            }\
            """;

    public static final String getTransactionResponse = """
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

    public static final String getBestBlockHashResponse = """
            {
            	"result": "00000000000000000024c244f9c7d1cc0e593a7a4aa31c1ee2ef35206934bfff",
            	"error": null,
            	"id": null
            }\
            """;

    public static final String getTransactionPoolInfoResponse = """
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

    public static final String getBlockchainInfoResponsePretty = """
            {
            	"result": {
            		"chain": "main",
            		"blocks": 531489,
            		"headers": 531489,
            		"bestblockhash": "0000000000000000003092e0372f341f5e027e026612b79d24558211eb486909",
            		"difficulty": 5363678461481.357,
            		"mediantime": 1531318259,
            		"verificationprogress": 0.9999863833408901,
            		"initialblockdownload": false,
            		"chainwork": "00000000000000000000000000000000000000000253f0139546a883ac201446",
            		"size_on_disk": 198571576396,
            		"pruned": false,
            		"softforks": [
            			{
            				"id": "bip34",
            				"version": 2,
            				"reject": {
            					"status": true
            				}
            			},
            			{
            				"id": "bip66",
            				"version": 3,
            				"reject": {
            					"status": true
            				}
            			},
            			{
            				"id": "bip65",
            				"version": 4,
            				"reject": {
            					"status": true
            				}
            			}
            		],
            		"bip9_softforks": {
            			"csv": {
            				"status": "active",
            				"startTime": 1462060800,
            				"timeout": 1493596800,
            				"since": 419328
            			},
            			"segwit": {
            				"status": "active",
            				"startTime": 1479168000,
            				"timeout": 1510704000,
            				"since": 481824
            			}
            		},
            		"warnings": ""
            	},
            	"error": null,
            	"id": null
            }\
            """;


    public static final String getBlockchainInfoResponse = """
            {
            	"result": {"chain": "main"},
            	"error": null,
            	"id": null
            }\
            """;


    // from dash client
    public static final String getMempoolResponse = """
            {
                "result": ["5bc76af67921c657c1c321de16a0403671365c6c07376a814b5de28c02ebe09b", "22121a969bd36559f38eb4d01850d044b34d5d75d912ba10969e4f64fd345be9", "24219415fc41d5205c455b3e1aef2b9323c3a2f5bc82eb2d08c1ea4336b3d3e5", "4607202d56516e4f10af26ada8f210c4802e195e8d43e5ef7f7470d2d0171c9c"],
                "error": null,
                "id": "curltest"
            }\
            """;


}

