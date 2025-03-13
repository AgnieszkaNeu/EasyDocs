INSERT INTO app_user (email, first_name, last_name, password, role, job_title, phone_number, description, user_creation_date) VALUES

    ('john.doe@corporation.com', 'John', 'Doe',
        '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
        'ROLE_ADMIN', 'Administrator', '123-123-123',
        'IT Administrator',
        ' 2023-07-12'),

    ('emily.smith@corporation.com', 'Emily', 'Smith',
    '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
    'ROLE_USER', 'Manager', '234-234-234',
    'Responsible for supply chain management and transport logistics.',
    ' 2022-11-15'),

    ('michael.johnson@corporation.com', 'Michael', 'Johnson',
    '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
    'ROLE_USER', 'Marketing Specialist', '234-234-234',
    'Marketing specialist, deals with advertising strategy and promotional campaigns.',
    ' 2022-11-15'),

    ('robert.anderson@corporation.com', 'Robert', 'Anderson',
    '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
    'ROLE_USER', 'HR Manager', '234-234-234',
    'Responsible for recruitment and maintaining personnel documentation.',
    ' 2023-06-01'),

    ('philip.sandrew@corporation.com', 'Philip', 'Sandrew',
    '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
    'ROLE_USER', 'Recruitment specialist', '234-234-234',
    'Responsible for recruitment.',
    ' 2023-06-01'),

    ('lisa.white@corporation.com', 'Lisa', 'White',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'Sales Representative', '345-345-345',
     'Handles client relationships and sales negotiations.',
     '2023-08-21'),

    ('david.brown@corporation.com', 'David', 'Brown',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'Financial Analyst', '456-456-456',
     'Responsible for financial forecasting and budget planning.',
     '2023-04-10'),

    ('susan.miller@corporation.com', 'Susan', 'Miller',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'Project Manager', '567-567-567',
     'Oversees project timelines and deliverables.',
     '2022-12-05'),

    ('james.wilson@corporation.com', 'James', 'Wilson',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'Software Engineer', '678-678-678',
     'Develops and maintains software applications.',
     '2023-03-18'),

    ('patricia.taylor@corporation.com', 'Patricia', 'Taylor',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'Quality Assurance Engineer', '789-789-789',
     'Ensures product quality through testing and verification.',
     '2023-07-07'),

    ('charles.moore@corporation.com', 'Charles', 'Moore',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'Business Analyst', '890-890-890',
     'Analyzes business needs and proposes solutions.',
     '2022-10-25'),

    ('jennifer.jackson@corporation.com', 'Jennifer', 'Jackson',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'Customer Support Specialist', '901-901-901',
     'Handles customer inquiries and support requests.',
     '2023-01-15'),

    ('matthew.harris@corporation.com', 'Matthew', 'Harris',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'IT Support Technician', '012-012-012',
     'Provides IT support and troubleshooting assistance.',
     '2023-05-30'),

    ('sarah.clark@corporation.com', 'Sarah', 'Clark',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'Data Analyst', '123-987-654',
     'Analyzes and interprets data to support decision-making.',
     '2023-09-12'),

    ('daniel.lewis@corporation.com', 'Daniel', 'Lewis',
     '$2a$10$/X.wUSKcOHTKXHqcrzGwxe.xBsmuXfj3nDCn81KK1B0uqkn/CgW2m',
     'ROLE_USER', 'Security Analyst', '321-654-987',
     'Monitors and ensures IT security within the company.',
     '2022-08-14');

INSERT INTO access_group (name, user_id) VALUES

     ('HR Documents', 4),
     ('IT Policies & Security', 1),
     ('Remote Work Resources', 1),
     ('Marketing Campaigns', 3);

INSERT INTO user_group_access (user_id, group_access_id) VALUES
    (5,1), (4,1),
    (1,2), (9,2), (13,2), (15,2),
    (1,3), (14,3), (15,3),
    (3,4), (2,4);