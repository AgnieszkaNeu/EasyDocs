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
    'USER', 'HR', '234-234-234',
    'Responsible for recruitment and maintaining personnel documentation.',
    ' 2023-06-01');

INSERT INTO document (name, file_type, user_id, creation_date, file_path, description) VALUES

    ('Work regulations 2025', 'docx', 4, '2025-01-10', 'C:\Users\agnne\uploads\Work regulations.docx',
    'Current work regulations, including rules regarding working time, leaves and employee duties'),

    ('Marketing campaign plan for spring 2025', 'pdf', 3, '2025-01-20', 'C:\Users\agnne\uploads\Marketing campaign plan for spring 2025.pdf',
    'Goals, assumptions and plan for a marketing campaign whose main theme will be the body positive movement' ),

    ('Procedure for accepting goods into the warehouse', 'pdf', 2, '2025-02-10', 'C:\Users\agnne\uploads\Procedure for accepting goods into the warehouse.pdf',
    'A document describing the steps and requirements for accepting deliveries into a warehouse'),

    ('VPN configuration', 'txt', 1, '2025-02-12', 'C:\Users\agnne\uploads\Instrukcja_konfiguracji_VPN.txt',
    'A step-by-step guide to setting up remote access to your corporate VPN for employees'),

    ('Employment contract template', 'docx', 4, '2025-01-22', 'C:\Users\agnne\uploads\Employment contract template.docx',
    'A standard template of an employment contract used in the company for new employees');
