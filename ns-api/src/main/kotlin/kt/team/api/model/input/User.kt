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


/**
 * 
 * @param id 
 * @param lastLogin 
 * @param birthDate 
 * @param sex 
 * @param location 
 * @param products 
 * @param tags 
 */
data class User (

    val id: kotlin.String? = null,
    val lastLogin: java.time.LocalDateTime? = null,
    val birthDate: java.time.LocalDateTime? = null,
    val sex: kotlin.String? = null,
    val location: kotlin.String? = null,
    val products: kotlin.Array<kotlin.String>? = null,
    val tags: kotlin.Array<kotlin.String>? = null
) {
}