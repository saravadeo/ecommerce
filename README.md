# ecommerce
Sample ecommerce backend with spring boot

Features
-------------

### Item
                
`POST` : <http://127.0.0.1:9091/item/create> (Post method below request body)

`Request`

    {
		"name" : "Pen",
		"description" : "New pen - with more details",
		"quantity" : 12
	}
`Response`


     {
		"uuid": "cc1dd186-133d-43e4-a267-5b5375911ce3",
     }
-------------

`GET` : <http://127.0.0.1:9091/item?uuid=cc1dd186-133d-43e4-a267-5b5375911ce3> (Get method below request param `uuid`)

`Response`

     {
    	"uuid": "cc1dd186-133d-43e4-a267-5b5375911ce3",
    	"name": "onkar12",
    	"description": "description",
    	"quantity": 12
	}
                
### Order
`POST` : <http://127.0.0.1:9091/order/place> (Post method below request body)

`Request`

    {
		"userEmail": "onkar.saravade@kyepot.com",
	"	items" : [{
			"uuid" : "0e82560e-637d-4e75-9f7b-eac6118c3ed9",
			"quantity" : 5
		}]
	}
`Response`


     {
    	"uuid": "6b7e224c-a871-4daa-8c0d-9ba17805e1a8",
	 }
-------------

`GET` : <http://127.0.0.1:9091/order?uuid=6b7e224c-a871-4daa-8c0d-9ba17805e1a8> (Get method below request param `uuid`)

`Response`

     {
    	"uuid": "6b7e224c-a871-4daa-8c0d-9ba17805e1a8",
    	"userEmail": "onkar.saravade@kyepot.com",
    	"items": [
       		{
            	"uuid": "0e82560e-637d-4e75-9f7b-eac6118c3ed9",
            	"name": "onkar12",
            	"description": "description",
            	"quantity": 5
        	}
    	]
	}

##### With many others endpoint to update item, delete item, get all item, get all orders etc.
