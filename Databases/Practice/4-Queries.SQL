USE Recipes;

-- 1) ������� ����� ��� ����, �� �� ������ ������������.
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

-- 2) ������� ����� ��� ����, �� ������� ��� ������������.
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

-- 3) ������� ������� ��� ������ � ��������������.
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

-- 4) ������� �� �������, � ���� ���� � ����� �Pudding�.
SELECT RecipeID, 
       RecipeTitle, 
       RecipeClassID, 
       Preparation, 
       Notes
FROM Recipes
WHERE RecipeTitle LIKE '%Pudding%';

-- 5) ������� ����� ������� ��� �����, �� ���� ������� �� �����, ��� 1 ����� ����� ���.
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

-- 6) ��� ������� �� ������� ������� (��������� 1) ��������� ��������. ����� �����䳺���	ʳ������	����� ������� ����� (����� �����, ������, ����)
SELECT ingredients.IngredientName, 
       recipe_ingredients.Amount, 
       measurements.MeasurementDescription
FROM INGREDIENTS ingredients
     JOIN RECIPE_INGREDIENTS recipe_ingredients ON ingredients.IngredientID = recipe_ingredients.IngredientID
                                                   AND recipe_ingredients.RecipeID = 1
     JOIN MEASUREMENTS measurements ON measurements.MeasureAmountID = recipe_ingredients.MeasureAmountID;

-- 7) ������� ����� ����� � ��������?
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

-- 8) ������ � ������� ������� � �����? ���������� �� � ������� �������� ������� �������.
SELECT RecipeClassID, 
       COUNT(RecipeClassID) AS RecipeClassCount
FROM RECIPES
GROUP BY RecipeClassID
ORDER BY RecipeClassCount DESC;

-- 9) ������� �� ������, ��� ������������ ���� ������� �������.
SELECT RecipeTitle, 
       Preparation
FROM RECIPES
WHERE Preparation LIKE '%blender%';

--10)��� ������� ��������� ��������: ����� �������	�����䳺���, �� ������� �� �������	���� ������� ������������
SELECT recipes.RecipeTitle, 
       ingredients.IngredientName, 
       recipes.Preparation
FROM RECIPES recipes
     JOIN RECIPE_INGREDIENTS recipe_ingredients ON recipes.RecipeID = recipe_ingredients.RecipeID
     JOIN RECIPE_CLASSES recipe_classes ON recipes.RecipeClassID = recipe_classes.RecipeClassID
                                           AND recipe_classes.RecipeClassDescription = 'Dessert'
     JOIN INGREDIENTS ingredients ON recipe_ingredients.IngredientID = ingredients.IngredientID;