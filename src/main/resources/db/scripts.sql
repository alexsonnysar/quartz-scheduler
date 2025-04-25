CREATE TABLE public.record_retention_policy (
                                                id SERIAL PRIMARY KEY,
                                                table_name VARCHAR(255) NOT NULL,
                                                retention_days INT NOT NULL
);

INSERT INTO public.record_retention_policy (table_name, retention_days)
VALUES ('receipts', 1);

CREATE TABLE public.receipts2 (
                                 id SERIAL PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL,
                                 created_date TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO public.receipts (name, created_date)
VALUES
    ('Old Receipt 1', NOW() - INTERVAL '2 days'), -- Older than 1 day
    ('Old Receipt 2', NOW() - INTERVAL '3 days'); -- Older than 1 day

INSERT INTO public.receipts (name, created_date)
SELECT
    'Receipt ' || gs.id AS name,
    NOW() - INTERVAL '2 day' * gs.id AS created_date
FROM
    generate_series(1, 100000) AS gs(id);


select * from public.receipts;

select * from public.record_retention_policy;

select * from qrtz_job_details



DELETE FROM public.receipts WHERE created_date <= NOW() - INTERVAL '1 day';