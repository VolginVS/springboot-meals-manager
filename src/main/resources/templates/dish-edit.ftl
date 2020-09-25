<#import "parts/common.ftl" as c>

<@c.page>
    <h2>Dish "${dish.name}"</h2>

    <div class="navi" >
        <a href="/dishes">Dishes page</a> |
        <a href="/foods">Foods page</a> |
    </div>
    <div class="error"> 
        <#if errorMessage??>
            ${errorMessage}
        </#if>
    </div>

    <div class="add">


            <form  action="${'/dish-' + dish.id + '-edit/name'}" name="d" method="POST">
                <td>Dish name:</td>
                <td> <input type="text" name="dishName" value="${dish.name}"/></td>
                <td><button type="submit">Update</button></td>
                </form>
    </div>
    <div>
        </tr>  
         <table border="0">     
        <tr>
            <td>Add ingredient<td>
        </tr>
        <tr>
            <form  method="POST">

                <td><select name="foodId">
                    <option label="---Food---"> </option>
                    <#list foodList as food>
                        <option label = '${food.name}, ${food.species}'>
                              ${food.id}
                        </option>
                   </#list>
                </select></td>
                <td><input type="number" name="servingWeight" placeholder="Serving weight"/></td>
                <td><button type="submit">Add</button></td>
            </form>
        </tr>

            <tr>
                <td>Ingredient:</td>    
                <td> Serving weight:</td>
                <td>  </td>
            </tr>

            <#list dishIngredientList as dishIngredient>
              <form  action="${'/dish-' + dish.id + '-edit/ingredient-'+dishIngredient.id}" name="d" method="POST">
                <tr>

                    <td><select name="foodId">
                        <#list foodList as food>
                            <option label='${food.name+', ' +food.species}'  ${(food.id==dishIngredient.food.id)?then('selected','')}   >
                                  ${food.id}
                            </option>
                       </#list>
                    </select></td>
                    <td><input type="number" name="servingWeight" value="#{dishIngredient.servingWeight}" /></td>  
                    <td> <button type="submit">Update</button><td>
                    <td><a href="${'/dish-' + dish.id + '-edit/ingredient-'+dishIngredient.id+'-delete'}">Delete</a></td>

                </tr>
              </form>
            <#else>
               <tr> <td> (Not any ingredient) <td> </tr>
            </#list>
    </div>
</@c.page>
