# Spring-Boot-Todo-Management
The project involves the creation of a comprehensive TODO management system using the Spring Boot framework, enhanced with the integration of Spring Security. The TODO management system provides users with a platform to organize and track tasks effectively. Leveraging the capabilities of Spring Boot, the application ensures seamless development, deployment, and maintenance.

# Motivation
In the modern era, with numerous tasks demanding completion, monitoring the progress of each task has become an arduous and time-consuming endeavor. Regardless of an individual's memory capabilities, the likelihood of failing to track certain tasks remains a concern. Thus, this project has been developed to tackle this challenge and facilitate effective task monitoring through the utilization of REST APIs. Separate to-do lists are designated for various individuals. Consequently, it is imperative to establish a system that ensures the isolation of each user's to-do list from others. To address this concern, Spring Security has been integrated to regulate access to to-do lists based on user and admin roles, thereby enhancing the security and confidentiality of these resources.
# Requirements
PostMan
Intellij Idea
MySQL

# Utilized Spring Security Features:
- Employed SpringBoot's fundamental form-based authentication.
- Employed SpringBoot's basic authentication and verified it using postman client.
- Incorporated SpringBoot's functionality for role-based access control.
- Modified default database credentials, providing a randomized password for enhanced security.
- Successfully integrated database authentication feature.

# Initial Setup:
Upon extracting and uploading the code files in IntelliJ Idea, it's essential to configure the MySQL datasource link. Additionally, the MySQL username and password must be adjusted to match your personal credentials.

# Methods of Database Authentication Implemented:

1. Depending on the requested API: To enable this approach for managing your to-do list, you must incorporate role-based tags within the application.properties file. You'll also need to deactivate specific lines within the "securityFilterChain" function in the configuration file, along with commenting out the lines that are relevant to the customer details object.

2. Leveraging Spring Data JPA: This method requires no modifications; you can execute the code as-is. The necessary entities for users, roles, and their respective join tables are already defined using the @Entity tag in their corresponding class files.
