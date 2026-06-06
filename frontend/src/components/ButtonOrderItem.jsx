export default function ButtonOrderItem({page, handlePage}){
    const cssButtonsPage = "px-3 py-1 bg-white rounded-md shadow disabled:opacity-40";

    return(
        <div className="fixed bottom-6 left-1/2 -translate-x-1/2 flex gap-3">
            <button className={cssButtonsPage} onClick={() => handlePage(page - 1)}>
                Anteiror
            </button>
            
            <button className={cssButtonsPage} onClick={() => handlePage(page + 1)}>
                Proximo
            </button>
        </div>
    );
}