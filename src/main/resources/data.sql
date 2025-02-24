INSERT INTO app_user (email, first_name, last_name, password, role, job_title, phone_number, description, user_creation_date) VALUES

    ('john.doe@corporation.com', 'John', 'Doe',
        '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
        'ADMIN', 'Administrator', '123-123-123',
        'IT Administrator',
        ' 2023-07-12'),

    ('emily.smith@corporation.com', 'Emily', 'Smith',
    '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
    'USER', 'Manager', '234-234-234',
    'Responsible for supply chain management and transport logistics.',
    ' 2022-11-15'),

    ('michael.johnson@corporation.com', 'Michael', 'Johnson',
    '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
    'USER', 'Marketing Specialist', '234-234-234',
    'Marketing specialist, deals with advertising strategy and promotional campaigns.',
    ' 2022-11-15'),

    ('robert.anderson@corporation.com', 'Robert', 'Anderson',
    '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
    'USER', 'HR Manager', '234-234-234',
    'Responsible for recruitment and maintaining personnel documentation.',
    ' 2023-06-01'),

    ('philip.sandrew@corporation.com', 'Philip', 'Sandrew',
    '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
    'USER', 'Recruitment specialist', '234-234-234',
    'Responsible for recruitment.',
    ' 2023-06-01'),

    ('lisa.white@corporation.com', 'Lisa', 'White',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'Sales Representative', '345-345-345',
     'Handles client relationships and sales negotiations.',
     '2023-08-21'),

    ('david.brown@corporation.com', 'David', 'Brown',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'Financial Analyst', '456-456-456',
     'Responsible for financial forecasting and budget planning.',
     '2023-04-10'),

    ('susan.miller@corporation.com', 'Susan', 'Miller',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'Project Manager', '567-567-567',
     'Oversees project timelines and deliverables.',
     '2022-12-05'),

    ('james.wilson@corporation.com', 'James', 'Wilson',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'Software Engineer', '678-678-678',
     'Develops and maintains software applications.',
     '2023-03-18'),

    ('patricia.taylor@corporation.com', 'Patricia', 'Taylor',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'Quality Assurance Engineer', '789-789-789',
     'Ensures product quality through testing and verification.',
     '2023-07-07'),

    ('charles.moore@corporation.com', 'Charles', 'Moore',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'Business Analyst', '890-890-890',
     'Analyzes business needs and proposes solutions.',
     '2022-10-25'),

    ('jennifer.jackson@corporation.com', 'Jennifer', 'Jackson',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'Customer Support Specialist', '901-901-901',
     'Handles customer inquiries and support requests.',
     '2023-01-15'),

    ('matthew.harris@corporation.com', 'Matthew', 'Harris',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'IT Support Technician', '012-012-012',
     'Provides IT support and troubleshooting assistance.',
     '2023-05-30'),

    ('sarah.clark@corporation.com', 'Sarah', 'Clark',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'Data Analyst', '123-987-654',
     'Analyzes and interprets data to support decision-making.',
     '2023-09-12'),

    ('daniel.lewis@corporation.com', 'Daniel', 'Lewis',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'USER', 'Security Analyst', '321-654-987',
     'Monitors and ensures IT security within the company.',
     '2022-08-14');


INSERT INTO document (name, file_type, user_id, creation_date, file_path, description, is_public) VALUES

    ('Work regulations 2025', 'docx', 4, '2025-01-10', 'C:\Users\agnne\uploads\Work regulations.docx',
    'Current work regulations, including rules regarding working time, leaves and employee duties', false),

    ('Marketing campaign plan for spring 2025', 'pdf', 3, '2025-01-20', 'C:\Users\agnne\uploads\Marketing campaign plan for spring 2025.pdf',
    'Goals, assumptions and plan for a marketing campaign whose main theme will be the body positive movement', false ),

    ('Procedure for accepting goods into the warehouse', 'pdf', 2, '2025-02-10', 'C:\Users\agnne\uploads\Procedure for accepting goods into the warehouse.pdf',
    'A document describing the steps and requirements for accepting deliveries into a warehouse', false),

    ('VPN configuration', 'txt', 1, '2025-02-12', 'C:\Users\agnne\uploads\Instrukcja_konfiguracji_VPN.txt',
    'A step-by-step guide to setting up remote access to your corporate VPN for employees', false),

    ('Employment contract template', 'docx', 4, '2025-01-22', 'C:\Users\agnne\uploads\Employment contract template.docx',
    'A standard template of an employment contract used in the company for new employees', false),

    ('Annual financial report 2024', 'pdf', 4, '2025-01-15', 'C:\Users\agnne\uploads\Annual financial report 2024.pdf',
    'A detailed financial report summarizing the company’s financial performance for the year 2024', false),

    ('Cybersecurity policy', 'docx', 3, '2025-02-05', 'C:\Users\agnne\\uploads\Cybersecurity policy.docx',
    'Company policy outlining security protocols, data protection measures, and IT security best practices', false),

    ('Onboarding checklist', 'xlsx', 2, '2025-01-25', 'C:\Users\agnne\uploads\Onboarding checklist.xlsx',
    'A checklist for HR to ensure all onboarding steps are completed for new employees', false),

    ('Project management guidelines', 'pdf', 3, '2025-02-08', 'C:\Users\agnne\uploads\Project management guidelines.pdf',
    'Best practices and frameworks for managing internal projects effectively', false),

    ('Salary structure overview', 'docx', 4, '2025-01-30', 'C:\Users\agnne\uploads\Salary structure overview.docx',
    'An overview of the company’s salary ranges, including bonuses and benefits', false),

    ('Employee code of conduct', 'pdf', 4, '2025-01-18', 'C:\Users\agnne\uploads\Employee code of conduct.pdf',
    'A document specifying ethical standards, company values, and expected behavior of employees', false),

    ('Warehouse safety procedures', 'pdf', 2, '2025-02-11', 'C:\Users\agnne\uploads\Warehouse safety procedures.pdf',
    'Safety protocols for employees working in warehouses, including emergency response plans', false),

    ('Remote work guidelines', 'docx', 3, '2025-01-27', 'C:\Users\agnne\uploads\Remote work guidelines.docx',
    'A guide to remote work policies, including IT security, communication, and performance expectations', false),

    ('Meeting agenda template', 'txt', 1, '2025-02-03', 'C:\Users\agnne\uploads\Meeting agenda template.txt',
    'Integration trip plan', true);


INSERT INTO access_group (name, user_id) VALUES

     ('HR Documents', 4),
     ('IT Policies & Security', 1),
     ('Remote Work Resources', 1),
     ('Marketing Campaigns', 3);

INSERT INTO document_group_access (document_id , group_access_id) VALUES

    (1,1), (5,1), (10,1), (11,1), (8,1),
    (4,2), (7,2),
    (13,3),
    (2,4);

INSERT INTO user_group_access (user_id, group_access_id) VALUES
    (5,1), (4,1),
    (1,2), (9,2), (13,2), (15,2),
    (1,3), (14,3), (15,3),
    (3,4), (2,4);