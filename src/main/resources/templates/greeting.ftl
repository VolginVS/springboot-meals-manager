<#import "parts/common.ftl" as c>

<@c.page>
    <div>
    <h1><b>Hello, Guest!</b></h1>
    <p>This is application for meals management</p>
    <p>The application is under development, however some features have already been implemented. 
    Please select one of the sections: </p> 
    </div>
    <div class="greeting-navi">
        <a href="/foods">Foods</a> |
        <a href="/dishes">Dishs</a> |
        <a href="/meal-plan">Meal plan page</a>
    </div>
    <div><p><st>Food page</st> - page for adding and editing new food, Dish page 
      - for composing dishes using food. Meal schedule page - creating a meal schedule.</p> 
    </div>
</@c.page>
