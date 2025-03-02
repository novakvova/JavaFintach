import {Form, Input, Button, Upload, message, Select} from "antd";
import { PlusOutlined } from "@ant-design/icons";
import {IProductPostRequest} from "../../types/Product.ts";
import {useState} from "react";
// import {useCreateCategoryMutation} from "../../services/apiCategory.ts";
import {useNavigate} from "react-router-dom";
import {useCreateProductMutation} from "../../services/apiProduct.ts";
import {useGetCategoriesQuery} from "../../services/apiCategory.ts";
import {ICategory} from "../../types/Category.ts";
import {UploadFile} from "antd/es/upload/interface";
import {DragDropContext, Draggable, Droppable, DropResult} from "@hello-pangea/dnd";

const { TextArea } = Input;

const ProductCreatePage : React.FC = () => {
    const [form] = Form.useForm<IProductPostRequest>();
    const [fileList, setFileList] = useState<UploadFile[]>([]);
    const navigate = useNavigate();

    const [createProduct, { isLoading, error }] = useCreateProductMutation();

    const { data: categories = [], isLoading: isCategoriesLoading, error: categoriesError } = useGetCategoriesQuery();

    const handleFinish = async (values: IProductPostRequest) => {
        // @ts-ignore

        // const file = values.imageFile?.file as File;
        // const model : IProductPostRequest = { ...values, imageFile: file };
        // //console.log("Submit form ", model);
        // try {
        //     const resp = await createCategory(model);
        //     console.log("Resp server", resp);
        //     //form.resetFields();
        //     //setImageUrl(null); // Очистити попередній перегляд після відправки
        //     navigate("/");
        // } catch (error) {
        //     console.error(error);
        // }

    };


    const handleImageChange = (info: { fileList: UploadFile[] }) => {
        const newFileList = info.fileList.map((file, index) => ({
            ...file,
            uid: file.uid || Date.now().toString(),
            order: index,
        }));

        setFileList(newFileList);
    };

    const onDragEnd = (result: DropResult) => {
        if (!result.destination) return;
        const reorderedFiles = Array.from(fileList);
        const [movedFile] = reorderedFiles.splice(result.source.index, 1);
        reorderedFiles.splice(result.destination.index, 0, movedFile);
        setFileList(reorderedFiles);
    };

    const categoriesOption = categories.map((category: ICategory) => {
        return {
            value: category.id,
            label: category.name
        }
    })

    return (
        <>
            <div className="max-w-lg mx-auto bg-white p-6 rounded-2xl shadow-md">
                <h2 className="text-xl font-semibold mb-4">Додати продукт</h2>
                <Form form={form} layout="vertical" onFinish={handleFinish}>
                    <Form.Item
                        label="Назва"
                        name="name"
                        rules={[{ required: true, message: "Вкажіть назву категорії" }]}
                    >
                        <Input placeholder="Назва категорії" />
                    </Form.Item>

                    <DragDropContext onDragEnd={onDragEnd}>
                        <Droppable droppableId="upload-list" direction="horizontal">
                            {(provided) => (
                                <div ref={provided.innerRef} {...provided.droppableProps} className="flex flex-wrap gap-2">
                                    {fileList.map((file, index) => (
                                        <Draggable key={file.uid} draggableId={file.uid} index={index}>
                                            {(provided) => (
                                                <div
                                                    ref={provided.innerRef}
                                                    {...provided.draggableProps}
                                                    {...provided.dragHandleProps}
                                                >
                                                    <Upload
                                                        listType="picture-card"
                                                        fileList={[file]}
                                                        onRemove={() => {
                                                            const newFileList = fileList.filter(f => f.uid !== file.uid);
                                                            setFileList(newFileList);
                                                        }}
                                                    />
                                                </div>
                                            )}
                                        </Draggable>
                                    ))}
                                    {provided.placeholder}
                                </div>
                            )}
                        </Droppable>
                    </DragDropContext>

                    <Upload
                        multiple
                        listType="picture-card"
                        beforeUpload={() => false}
                        onChange={handleImageChange}
                        fileList={[]}
                        accept="image/*"
                    >
                        <div>
                            <PlusOutlined/>
                            <div style={{marginTop: 8}}>Додати</div>
                        </div>
                    </Upload>



                    { isCategoriesLoading ?
                        <p>Loading categories...</p>
                        :
                        categoriesError ?
                            <div className={"text-red-500"}>Fail Load Categories</div>
                            :
                            <Form.Item
                                label="Категорія"
                                name="categoryId"
                                htmlFor="categoryId"
                                rules={[{required: true, message: "Це поле є обов'язковим!"}]}
                            >
                                <Select placeholder="Оберіть категорію: " options={categoriesOption}/>
                            </Form.Item>
                    }

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

export default  ProductCreatePage;