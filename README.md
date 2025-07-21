# Job Application Processing Pipeline

A Spring Boot project that automates CV/resume processing for job applications. This application receives user-submitted CVs and details, extracts key information using Apache Tika and AI(Gemini), uploads the CV file to cloud storage, saves extracted data and accessible link of CV file for retrieval in a database, and sends a confirmation email to the applicant.

---

## Tech Stack

| Technology    | Usage                                    |
|---------------|------------------------------------------|
| Spring Boot   | Backend framework                        |
| Apache Tika   | CV text extraction and checks file types |
| Google Gemini | Parses info from CV                      |
| Supabase      | Cloud file storage                       |
| MongoDB       | Relational database                      |
| Spring Mail   | Email sending                            |
| Java 21       | Language                                 |

---

## How It Works

1. **User submits** their name, email,contact No, Job Title and CV via REST API (multipart/form).
2. **Custom Validation** Ensure input fields and file types are properly formatted.
3. **Apache Tika** extracts text from the CV.
4. **Gemini AI** parses additional user info, education, experience, and projects etc.
5. **StorageService** uploads the CV to cloud (Supabase).
6. **Database** stores user data and CV URL in database (MongoDB).
7. **EmailService**-gmail sends a confirmation email to the user.
8. **Custom Exceptions** Define and throw custom exceptions to manage errors cleanly.

---

## API Endpoint

### POST `/api/v1/application`

**Body - Form Data:**

| Key  | Value-Type      |
|------|-----------------|
| data | `Json`          |
| file | `MultipartFile` |

**Data - Json Fields:**

| Field     | Type      |
|-----------|-----------|
| name      | `String`  |
| email     | `String`  |
| contactNo | `String`  |
| jobTitle  | `String`  |

**Returns:** `200 OK` with confirmation.

---

## Environment Variables

Set these in `.env`:
````
GOOGLE_API_KEY=

SUPABASE_URL=
SUPABASE_BUCKET=
SUPABASE_API_KEY=

MONGODB_USER=
MONGODB_PASSWORD=
MONGODB_CLUSTER=
MONGODB_DATABASE=

MAIL_HOST=
MAIL_PORT=
MAIL_USERNAME=
MAIL_PASSWORD=

MAIL_ORGANIZATION_EMAIL=
````

---

## Spring Boot Dependencies Used

- Spring Web
- Apache Tika
- Lombok
- Google AI Driver
- MongoDB Driver
- Spring Mail