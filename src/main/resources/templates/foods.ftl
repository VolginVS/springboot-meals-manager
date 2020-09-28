<#import "parts/common.ftl" as c>

<@c.page>
        <h1>Foods</h1>
        <div class="navi" >
            <a href="/meal-plan">Meal plan page</a> |
            <a href="/dishes">Dishes page</a> |
        </div>    
 
  
    <div class="error"> 
        <#if errorMessage??>
            ${errorMessage}
        </#if>
    </div>


  <div class="add">
    <b>Add new food</b>
    <form method="post">

        <input type="text" name="name" placeholder="Название продукта" />
        <input type="text" name="species" placeholder="Вид"/>
        <button type="submit">Добавить</button>
    </form>
</div>
        <div class="food" id='food-list'>
            <b>List of food</b>
            <table>
                <tr>
                    <th>Наименование продукта</th>
                    <th>Вид</th>
                    <th colspan="2">Редактировать</th>                
     
                </tr>
                <#list foodList as food>
                    <tr  data-id="${food.id}">
                        <td>${food.name}</td>
                        <td>${food.species}</td>
                        <td class="edit"><a href="${'food-'+food.id+'-edit'}">Edit</a></td>
                        <td class="edit"><a href="${'food-'+food.id+'-delete'}">delete</a></td>
                    </tr>
                <#else>
                    No food
                </#list>
            </table>          
        </div>
        <br/><br/>
    

</@c.page>
