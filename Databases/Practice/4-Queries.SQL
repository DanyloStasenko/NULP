USE Recipes;

-- 1) Виведіть назви всіх блюд, які не містять морепродуктів.
SELECT RecipeTitle
FROM Recipes
WHERE RecipeID IN
(
    SELECT RecipeID
    FROM RECIPE_INGREDIENTS recipe_ingredients
         JOIN INGREDIENTS ingredients ON ingredients.IngredientID = recipe_ingredients.IngredientID
                                         AND ingredients.IngredientClassID !=
    (
        SELECT IngredientClassID
        FROM INGREDIENT_CLASSES
        WHERE IngredientClassDescription = 'Seafood'
    )
);

-- 2) Виведіть назви всіх блюд, які підійдуть для вегетаріанців.
SELECT RecipeTitle
FROM Recipes
WHERE RecipeID IN
(
    SELECT RecipeID
    FROM RECIPE_INGREDIENTS recipe_ingredients
         JOIN INGREDIENTS ingredients ON ingredients.IngredientID = recipe_ingredients.IngredientID
                                         AND ingredients.IngredientClassID NOT IN
    (
        SELECT IngredientClassID
        FROM INGREDIENT_CLASSES
        WHERE IngredientClassDescription = 'Meat'
		OR IngredientClassDescription = 'Seafood'
    )
);

-- 3) Знайдіть рецепти всіх салатів з морепродуктами.
SELECT RecipeTitle
FROM Recipes
WHERE RecipeID IN
(
    SELECT RecipeID
    FROM RECIPE_INGREDIENTS recipe_ingredients
		 JOIN RECIPE_CLASSES recipe_classes ON Recipes.RecipeClassID = 
	(
	Select RecipeClassID from RECIPE_CLASSES
	WHERE RecipeClassDescription = 'Salad'
	)
         JOIN INGREDIENTS ingredients ON ingredients.IngredientID = recipe_ingredients.IngredientID
                                         AND ingredients.IngredientClassID =
    (
        SELECT IngredientClassID
        FROM INGREDIENT_CLASSES
        WHERE IngredientClassDescription = 'Seafood'
    )
);

-- 4) Знайдіть всі рецепти, в назві яких є слово «Pudding».
SELECT RecipeID, 
       RecipeTitle, 
       RecipeClassID, 
       Preparation, 
       Notes
FROM Recipes
WHERE RecipeTitle LIKE '%Pudding%';

-- 5) Виведіть назви рецептів всіх страв, до яких входить не більше, ніж 1 чайна ложка солі.
SELECT RecipeTitle
FROM Recipes
WHERE RecipeID IN
(
    SELECT RecipeID
    FROM RECIPE_INGREDIENTS recipe_ingredients
         JOIN INGREDIENTS ingredients ON ingredients.IngredientID = recipe_ingredients.IngredientID
                                         AND ingredients.IngredientName = 'Salt'
                                         AND recipe_ingredients.Amount <= 1
);

-- 6) Для рецепту із заданим номером (наприклад 1) сформуйте табличку. Назва інгредієнта	Кількість	Назва одиниць виміру (чайна ложка, стакан, тощо)
SELECT ingredients.IngredientName, 
       recipe_ingredients.Amount, 
       measurements.MeasurementDescription
FROM INGREDIENTS ingredients
     JOIN RECIPE_INGREDIENTS recipe_ingredients ON ingredients.IngredientID = recipe_ingredients.IngredientID
                                                   AND recipe_ingredients.RecipeID = 1
     JOIN MEASUREMENTS measurements ON measurements.MeasureAmountID = recipe_ingredients.MeasureAmountID;

-- 7) Рецептів якого класу є найбільше?
SELECT RecipeClassID, 
       COUNT(RecipeClassID) AS RecipeClassCount
FROM RECIPES
GROUP BY RecipeClassID
HAVING COUNT(RecipeClassID) =
(
    SELECT MAX(recipes.RecipeClassCount)
    FROM
    (
        SELECT RecipeClassID, 
               COUNT(RecipeClassID) AS RecipeClassCount
        FROM RECIPES
        GROUP BY RecipeClassID
    ) recipes
);

-- 8) Скільки є рецептів кожного з класів? розташуйте їх у порядку спадання кількості рецептів.
SELECT RecipeClassID, 
       COUNT(RecipeClassID) AS RecipeClassCount
FROM RECIPES
GROUP BY RecipeClassID
ORDER BY RecipeClassCount DESC;

-- 9) Виведіть всі страви, для приготування яких потрібен блендер.
SELECT RecipeTitle, 
       Preparation
FROM RECIPES
WHERE Preparation LIKE '%blender%';

--10)Для десертів сформуйте табличку: Назва десерту	Інгредієнти, що входять до десерту	Опис процесу приготування
SELECT recipes.RecipeTitle, 
       ingredients.IngredientName, 
       recipes.Preparation
FROM RECIPES recipes
     JOIN RECIPE_INGREDIENTS recipe_ingredients ON recipes.RecipeID = recipe_ingredients.RecipeID
     JOIN RECIPE_CLASSES recipe_classes ON recipes.RecipeClassID = recipe_classes.RecipeClassID
                                           AND recipe_classes.RecipeClassDescription = 'Dessert'
     JOIN INGREDIENTS ingredients ON recipe_ingredients.IngredientID = ingredients.IngredientID;