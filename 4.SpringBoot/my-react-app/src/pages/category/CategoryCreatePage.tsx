import { Form, Input, Button, Upload, message } from "antd";
import { PlusOutlined } from "@ant-design/icons";
import {ICategoryCreate} from "../../types/Category.ts";
import {useState} from "react";
import {useCreateCategoryMutation} from "../../services/apiCategory.ts";
import {useNavigate} from "react-router-dom";

const { TextArea } = Input;

const CategoryCreatePage : React.FC = () => {
    const [form] = Form.useForm<ICategoryCreate>();
    const [imageUrl, setImageUrl] = useState(null);
    const navigate = useNavigate();

    const [createCategory, { isLoading, error }] = useCreateCategoryMutation();

    const handleFinish = async (values: ICategoryCreate) => {
        // @ts-ignore
        const file = values.imageFile?.file as File;
        const model : ICategoryCreate = { ...values, imageFile: file };
        //console.log("Submit form ", model);
        try {
            const resp = await createCategory(model);
            console.log("Resp server", resp);
            //form.resetFields();
            //setImageUrl(null); // Очистити попередній перегляд після відправки
            navigate("/");
        } catch (error) {
            console.error(error);
        }

    };


    const handleUpload = (file: File) => {
        // @ts-ignore
        setImageUrl(URL.createObjectURL(file));
    };

    // Обмеження для файлів
    const beforeUpload = (file: File) => {
        const isImage = file.type.startsWith("image/");
        if (!isImage) {
            message.error("Можна завантажувати тільки зображення!");
            return false;
        }
        handleUpload(file);
        return false;
    };

    return (
        <>
            <div className="max-w-md mx-auto bg-white p-6 rounded-2xl shadow-md">
                <h2 className="text-xl font-semibold mb-4">Додати категорію</h2>
                <Form form={form} layout="vertical" onFinish={handleFinish}>
                    <Form.Item
                        label="Назва"
                        name="name"
                        rules={[{ required: true, message: "Вкажіть назву категорії" }]}
                    >
                        <Input placeholder="Назва категорії" />
                    </Form.Item>

                    {/* Завантаження фото */}
                    <Form.Item label="Зображення" name={"imageFile"}>
                        <Upload
                            showUploadList={false}
                            beforeUpload={beforeUpload}
                        >
                            <Button icon={<PlusOutlined />}>Завантажити фото</Button>
                        </Upload>
                    </Form.Item>

                    {/* Попередній перегляд фото */}
                    {imageUrl && (
                        <div className="mt-4">
                            <img
                                src={imageUrl}
                                alt="Preview"
                                style={{maxHeight: "100px", maxWidth: "100%"}}
                                // className="w-full h-40 object-cover rounded-lg shadow-md"
                            />
                        </div>
                    )}
                    {/* Опис категорії */}
                    <Form.Item
                        label="Опис"
                        name="description"
                        rules={[{ required: true, message: "Введіть опис категорії" }]}
                    >
                        <TextArea placeholder="Введіть опис" rows={4} />
                    </Form.Item>

                    <Form.Item>
                        <Button type="primary" htmlType="submit" className="w-full"
                                disabled={isLoading}>
                            {isLoading ? 'Створення...' : 'Створити категороію'}
                        </Button>
                    </Form.Item>

                    {error && <p className="text-red-500 mt-2">Error creating category!</p>}
                </Form>
            </div>


        </>
    )
}

export default  CategoryCreatePage;