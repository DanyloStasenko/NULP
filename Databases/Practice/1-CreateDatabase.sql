if not exists(select * from sys.databases where name = 'Recipes')
BEGIN
	PRINT 'Creating Database...'
    create database Recipes;
END
