# **Banking Microservices System**  
*A modular banking application built with Spring Boot, Spring Cloud, and Docker to explore microservices architecture, service discovery, and inter-service communication.*  

---

## **🛠️ Technologies**  
### **Backend**  
- **Java 21** + **Spring Boot 3**  
- **Spring Cloud**:  
  - **Eureka** (Service Discovery)  
  - **Gateway** (API Routing)  
  - **Config Server** (Centralized Config)  
- **OpenFeign** (Service-to-service calls)  
- **MySQL** (Persistence)  
- **OpenAPI 3** (API Documentation)  
### **DevOps**  
- **Docker** + **Docker Compose**  

---

## **📂 Repository Structure**  
```bash  
banking-microservices/  
├── accounts/          # Account management service  
├── cards/             # Card management service  
├── loans/             # Loan management service  
├── gateway-server/    # API Gateway (Spring Cloud Gateway)  
├── config-server/     # Centralized configuration  
├── eureka-server/     # Service registry (Eureka)  
├── docker-compose/    # Docker orchestration files  
└── README.md          # Project documentation  
```  

---

## **🚀 How to Run**  
### **Prerequisites**  
- Java 21  
- Docker  

### **Steps**  
1. Clone the repository:  
   ```bash  
   git clone https://github.com/said-nasser2001/banking-microservices.git  
   cd banking-microservices  
   ```  
2. Start all services:  
   ```bash  
   docker-compose up --build  
   ```  
3. Access:  
   - **API Gateway**: `http://localhost:8072`  
   - **Eureka Dashboard**: `http://localhost:8070`  
   - **OpenAPI Docs**: `http://localhost:8072/v3/api-docs` (per service)  

---

## **💡 Key Concepts**  

### **1. Service Discovery (Eureka)**  
- Services auto-register with Eureka.  


### **2. API Gateway (Spring Cloud Gateway)**  
- Routes requests to microservices.   

### **3. Centralized Configuration (Config Server)**  
- Manage `application.yml` for all services in one place.   

### **4. OpenAPI (Swagger) Integration**  
- **Automated API Documentation**:  
  ```java  
  @SpringBootApplication  
  @OpenAPIDefinition(info = @Info(title = "Account Service API", version = "1.0"))  
  public class AccountApplication { ... }  
  ```  
- Access Swagger UI per service:  
  ```  
  http://localhost:808X/swagger-ui.html  
  ```  

### **5. Error Handling**  
- **Global Exception Handling**
 

---

## **🔍 Monitoring & Debugging**  
| Tool               | URL                              |  
|--------------------|----------------------------------|  
| **Eureka**         | `http://localhost:8070`          |  
| **Swagger UI**     | `http://localhost:808X/swagger-ui.html` |  
| **Actuator Health**| `http://localhost:808X/actuator/health` |  

---

