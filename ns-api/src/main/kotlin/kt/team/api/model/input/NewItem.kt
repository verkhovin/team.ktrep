/**
* NSOA
* No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
*
* OpenAPI spec version: 1.0
* 
*
* NOTE: This class is auto generated by the swagger code generator program.
* https://github.com/swagger-api/swagger-codegen.git
* Do not edit the class manually.
*/
package io.swagger.client.models

import io.swagger.client.models.ContentType
import io.swagger.client.models.ItemBody

/**
 * 
 * @param type 
 * @param body 
 */
data class NewItem (
    val type: ContentType? = null,
    val body: ItemBody? = null
) {
}