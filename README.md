# furnishly
## Furniture API
The Furniture API provides endpoints for managing furniture-related operations. It allows you to retrieve information about furniture items, create new items, update existing ones, and delete items.

Endpoints
Get All Furniture Items
URL: /furniture/all
Method: GET
Description: Retrieves a list of all furniture items.
Response Format: JSON

Get Furniture Item by ID
URL: /furniture/{id}
Method: GET
Description: Retrieves details of a specific furniture item by ID.
Parameters:
{id}: The ID of the furniture item.
Response Format: JSON

Delete Furniture Item
URL: /furniture/{id}
Method: DELETE
Description: Deletes a furniture item by ID.
Parameters:
{id}: The ID of the furniture item.
Response:
Status code 204 (No Content) if successful.
Status code 404 (Not Found) if the item does not exist.
Status code 400 (Bad Request) for other errors.

etc.

## Manufacturer API
The Manufacturer API provides endpoints for managing manufacturer-related operations. It allows you to retrieve information about manufacturers, create new manufacturers, update existing ones, and delete manufacturers.

Endpoints
Get All Manufacturers
URL: /manufacturer/all
Method: GET
Description: Retrieves a list of all manufacturers.
Response Format: JSON

Get Manufacturer by ID
URL: /manufacturer/{id}
Method: GET
Description: Retrieves details of a specific manufacturer by ID.
Parameters:
{id}: The ID of the manufacturer.
Response Format: JSON

Delete Manufacturer
URL: /manufacturer/{id}
Method: DELETE
Description: Deletes a manufacturer by ID.
Parameters:
{id}: The ID of the manufacturer.
Response:
Status code 204 (No Content) if successful.
Status code 404 (Not Found) if the manufacturer does not exist.
Status code 400 (Bad Request) for other errors.

etc.

## Furniture Type API
The Furniture Type API provides endpoints for managing furniture types. It allows you to retrieve information about different types of furniture, create new types, update existing ones, and delete types.

Endpoints
Get All Furniture Types
URL: /type/all
Method: GET
Description: Retrieves a list of all furniture types.
Response Format: JSON

Get Furniture Type by ID
URL: /type/{id}
Method: GET
Description: Retrieves details of a specific furniture type by ID.
Parameters:
{id}: The ID of the furniture type.
Response Format: JSON

Delete Furniture Type
URL: /type/{id}
Method: DELETE
Description: Deletes a furniture type by ID.
Parameters:
{id}: The ID of the furniture type.
Response:
Status code 204 (No Content) if successful.
Status code 404 (Not Found) if the type does not exist.
Status code 400 (Bad Request) for other errors.

etc.